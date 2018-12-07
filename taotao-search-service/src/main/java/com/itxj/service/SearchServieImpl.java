package com.itxj.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itxj.pojo.Item;
import com.itxj.pojo.Page;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   SearchServieImpl
 *  @创建者:   小吉
 *  @创建时间:  2018/11/14 15:54
 *  @描述：         query          page           totalPages
 *               查询的条件    solr查询的数据        共多少页
 */
@Service
public class SearchServieImpl implements SearchServie {

    @Autowired
    private SolrClient solrClient;

    @Override
    public Page<Item> SearchItem(String q, Integer page) {

        System.out.println("q=" + q);



        Page<Item> pageItem=new Page();


        try {
            //从索引库中分页查询数据
            SolrQuery query=new SolrQuery();

            query.setQuery("item_title:"+q);
            //分页查询
            query.setStart((page-1)*16);//查询条件----查询开始的索引
            query.setRows(16);//查询条title件--页面显示数

             //设置是否高亮
            query.setHighlight(true);
            
            //添加高亮的字段
            query.addHighlightField("item_title");
            //设置高亮的前缀
            query.setHighlightSimplePre("<font color='red'>");
            //设置高亮的后缀
            query.setHighlightSimplePost("</font>");
            
           
            QueryResponse response = solrClient.query(query);

            SolrDocumentList results = response.getResults();

            //获取高亮的数据
            Map<String, Map<String, List<String>>> map = response.getHighlighting();

            //获取总记录数
            long total = results.getNumFound();
            System.out.println("总记录数=" + total);


            List<Item> itemList=new ArrayList<>();
            //数据集合  private List<T> list;

            for (SolrDocument result : results) {
                Item item=new Item();
                long id = Long.parseLong((String) result.getFieldValue("id"));

                List<String> HlTitle = map.get(id + "").get("item_title");
                if(HlTitle!=null && HlTitle.size()>0){

                    String t = HlTitle.get(0);
                    item.setTitle(t);
                }
                else {
                    item.setTitle( (String) result.getFieldValue("item_title") );
                }

                item.setId(id);
                item.setImage((String) result.getFieldValue("item_image"));
                item.setStatus((Integer) result.getFieldValue("item_status"));
                item.setPrice((Long) result.getFieldValue("item_price"));
                item.setCid((Long) result.getFieldValue("item_cid"));
                itemList.add(item);

            }

            //初始化分页
            pageItem.initPage(total,page,16);
            //封装数据
            pageItem.setList(itemList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("devtools测试中......01");
        return pageItem;
    }
}
