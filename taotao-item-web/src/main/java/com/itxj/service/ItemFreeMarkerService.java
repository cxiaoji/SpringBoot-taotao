package com.itxj.service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.itxj.pojo.Item;
import com.itxj.pojo.ItemDesc;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   ItemFreeMarkerService
 *  @创建者:   小吉
 *  @创建时间:  2018/12/5 16:09
 *  @描述：    TODO
 */
@Service
public class ItemFreeMarkerService {


    @Reference
    private ItemServive itemServive;

    @JmsListener(destination = "item_save")
    public void ItemSaveFreeMarker(String message) throws Exception {
        //测试activeMQ能否收到消息
        System.out.println("FreeMarker监听activeMQ收到的id：" + message);

        //使用FreeMarker创建静态商品详情页面
        //创建核心配置类
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        //设置模板目录
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\ftl";
        configuration.setDirectoryForTemplateLoading(new File(path));
        //获取模板
        Template template = configuration.getTemplate("item.ftl");

        //封装动态数据，生成静态html页面
        Map<String, Object> dateModel = new HashMap<>();
        //通过id查询商品数据
        Item item = itemServive.getItemById(Long.parseLong(message));
        //封装新增的商品的动态数据
        dateModel.put("item", item);
        //通过id查询商品介绍数据
        ItemDesc itemDesc = itemServive.getItemDescById(Long.parseLong(message));
        //封装新增的商品介绍的动态数据
        dateModel.put("itemDesc", itemDesc);
        //生成的页面输出的目录位置
        Writer out = new FileWriter(new File("E:\\taotao\\item\\" + message + ".html"));
        template.process(dateModel, out);
        //关闭输出流
        out.close();
    }

}
