package com.itxj.service;

import com.itxj.mapper.ItemMapper;
import com.itxj.pojo.Item;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   ItemServiceImpl
 *  @创建者:   小吉
 *  @创建时间:  2018/11/28 10:33
 *  @描述：    TODO
 */
@Service
public class ItemServiceImpl implements ItemService{


    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SolrClient solrClient;

    @JmsListener(destination = "item_save")
    @Override
    public void addItemSolr(String message) {

        System.out.println("收到生产者的消息"+message);

    Long id=Long.parseLong(message);
        //查询商品item信息----更新索引库
        Item item = itemMapper.selectByPrimaryKey(id);
        System.out.println("需要更新的商品信息："+item.toString());

        //更新索引
        //SolrInputDocument doc=new SolrInputDocument();
        //添加商品到文档---创建文档
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        solrInputDocument.addField("id",id);
        solrInputDocument.addField("item_title",item.getTitle());
        solrInputDocument.addField("item_image",item.getImage());
        solrInputDocument.addField("item_price",item.getPrice());
        solrInputDocument.addField("item_cid",item.getCid());
        solrInputDocument.addField("item_status",item.getStatus());

        try {

            solrClient.add(solrInputDocument);
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
