package com.itxj.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itxj.mapper.ItemDescMapper;
import com.itxj.mapper.ItemMapper;
import com.itxj.pojo.Item;
import com.itxj.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;

import java.util.Date;
import java.util.List;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   ItemServiveImpl
 *  @创建者:   小吉
 *  @创建时间:  2018/9/26 9:58
 *  @描述：    TODO
 */
@Service
public class ItemServiveImpl implements ItemServive {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Autowired
    private JmsMessagingTemplate template;

    //添加商品
    @Override
    public Integer addItem(Item item, String desc) {
        //先增加Item商品
        long id = (long) (System.currentTimeMillis() + Math.random() * 10000);
        item.setId(id);//商品id
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        int result01 = itemMapper.insertSelective(item);
        //增加desc描述
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDesc.setItemDesc(desc);
        int result02 = itemDescMapper.insertSelective(itemDesc);

        /////  activeMQ 消息队列更新搜索系统的索引库

        //创建消费者-----发送消息-----添加商品的id
        template.convertAndSend("item_save", id);

        System.out.println("生产者发送id=" + id);

        return result01 + result02;
    }


    //查询商品
    @Override
    public PageInfo<Item> listItem(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<Item> list = itemMapper.selectAll();
        return new PageInfo<>(list);
    }

    //通过id查询商品
    @Override
    public Item getItemById(Long id) {
        return itemMapper.selectByPrimaryKey(id);

    }

    //通过id查询商品详情介绍
    @Override
    public ItemDesc getItemDescById(Long id) {
        return itemDescMapper.selectByPrimaryKey(id);
    }


    //通过id删除商品
    @Override
    public Integer delItem(long id) {

        int i = itemMapper.deleteByPrimaryKey(id);
        return i;
    }

    //通过id下架商品
    @Override
    public Integer instockItem(long id) {
        Item item = new Item();
        item.setId(id);
        item.setStatus(0);
        //根据id修改商品的status为0

        return itemMapper.updateByPrimaryKeySelective(item);
    }

    //通过id上架商品
    @Override
    public Integer reshelfItem(long id) {
        //根据id修改商品的status为1
        Item item = new Item();
        item.setId(id);
        item.setStatus(1);
        return itemMapper.updateByPrimaryKeySelective(item);
    }

    //更新商品
    @Override
    public Integer updateItem(Item item) {
        item.setUpdated(new Date());
        return itemMapper.updateByPrimaryKeySelective(item);
    }

    //删除+上架+下架  商品
    @Override
    public Integer deleteAndInstockAndReshlfItemByItem(String action, String ids) {

        Integer result = 0;

        //切割字符串ids获得多个id
        String[] strings = ids.split(",");

        //2.判断商品的操作行为------删除action==delete；上架action==instock；下架action==reshelf

        //实例化操作对象
        Item item=new Item();

        //删除商品
        if (action.equals("delete")) {
            for (String id : strings) {
                result = itemMapper.deleteByPrimaryKey(Long.parseLong(id));
                if (result <= 0) break;

            }
        }

        //下架商品
        if (action.equals("instock")) {

            for (String id : strings) {
               item.setId(Long.parseLong(id));
               item.setStatus(0);
                item.setUpdated(new Date());

                result = itemMapper.updateByPrimaryKeySelective(item);
                if (result <= 0) break;

            }
        }

        //上架商品
        if (action.equals("reshelf")) {
            for (String id : strings) {
                item.setId(Long.parseLong(id));
                item.setStatus(1);
                item.setUpdated(new Date());
                result = itemMapper.updateByPrimaryKeySelective(item);

                if (result <= 0) break;

            }
        }

        return result;
    }
}
