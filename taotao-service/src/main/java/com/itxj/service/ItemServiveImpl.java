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
public class ItemServiveImpl implements ItemServive{

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper  itemDescMapper;

    @Autowired
    private JmsMessagingTemplate template;
    //添加商品
    @Override
    public Integer addItem(Item item, String desc) {
        //先增加Item商品
        long id= (long) (System.currentTimeMillis()+Math.random()*10000);
        item.setId(id);//商品id
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        int result01=itemMapper.insertSelective(item);
        //增加desc描述
        ItemDesc itemDesc=new ItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDesc.setItemDesc(desc);
        int result02=itemDescMapper.insertSelective(itemDesc);

 /////  activeMQ 消息队列更新搜索系统的索引库

        //创建消费者-----发送消息-----添加商品的id
        template.convertAndSend("item_save",id);

        System.out.println("生产者发送id=" + id);

        return result01+result02;
    }



//查询商品
    @Override
    public PageInfo<Item> listItem(int page, int rows) {
        PageHelper.startPage(page,rows);
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

    //更新商品
    @Override
    public Integer updateItem(Item item) {

        return itemMapper.updateByPrimaryKeySelective(item);
    }
}
