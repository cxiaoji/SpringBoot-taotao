package com.itxj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itxj.pojo.Item;
import com.itxj.service.ItemServive;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.controller
 *  @文件名:   SolrInitController
 *  @创建者:   小吉
 *  @创建时间:  2018/11/14 10:13
 *  @描述：    查询所有商品数据----导入到solr索引库
 */
@RestController
public class SolrInitController {

    @Reference
    private ItemServive itemServive;

    //TODO solr查询不到商品数据

    @Autowired
    private SolrClient solrClient;

    @RequestMapping("/init")
    public String init() throws IOException, SolrServerException {

        //查询的页数-----显示的条数
        int page=1,rows=500;

        //创建文档集合
        List<SolrInputDocument> solrDocumentList=new ArrayList<>();

        //查询所有商品数据
        do {
         //查询到的分页商品集合
        PageInfo<Item> itemPageInfo = itemServive.listItem(page, rows);

            System.out.println("数据查询成功==" + page);
            //循环查询条件
            rows=itemPageInfo.getSize();
        //循环遍历查询分页商品
        page++;


            //添加商品到文档---
            for (Item item : itemPageInfo.getList()) {
                //添加商品到文档---创建文档
                SolrInputDocument solrInputDocument=new SolrInputDocument();
                solrInputDocument.addField("id",item.getId());
                solrInputDocument.addField("item_title",item.getTitle());
                solrInputDocument.addField("item_image",item.getImage());
                solrInputDocument.addField("item_price",item.getPrice());
                solrInputDocument.addField("item_cid",item.getCid());
                solrInputDocument.addField("item_status",item.getStatus());

                //添加到文档集合
                solrDocumentList.add(solrInputDocument);

            }


    }while (rows==500);

        //将集合文档添加到solr索引库中
        solrClient.add(solrDocumentList);

     //提交
        solrClient.commit();

        return "success";
    }

}
