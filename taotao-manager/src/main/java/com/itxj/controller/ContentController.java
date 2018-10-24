package com.itxj.controller;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.controller
 *  @文件名:   ContentController
 *  @创建者:   小吉
 *  @创建时间:  2018/10/15 21:31
 *  @描述：    后台管理----内容管理
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itxj.pojo.Content;
import com.itxj.service.ContentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ContentController {


    @Reference
    private ContentService contentService;

    //广告新增
    @PostMapping("/rest/content")
    public String add(Content content){
        System.out.println("CategoryId---"+content.getCategoryId());
        contentService.add(content);
        return "success";
    }

    //广告内容分页查询
    @GetMapping("/rest/content")
    public Map<String,Object> list(Content content,int page,int rows){
        // rest/content?categoryId=89&page=1&rows=20
        //分页查询
        PageInfo<Content> list = contentService.list(content, page, rows);
        //easyui 的数据表格显示数据，要求有固定格式---要求有total和rows属性
        //total 属性 ----总共有多少条数据
        //rows 属性------当前页集合数据

        Map<String,Object> map=new HashMap<>();
        map.put("total",list.getTotal());
        map.put("rows",list.getList());

        return map;
    }

    //广告内容删除
    @RequestMapping("/rest/content/delete")
    public Map<String,Integer> delete(String ids){
        int result=contentService.delete(ids);
        //返回状态码，告知删除是否成功
        Map<String,Integer> map=new HashMap<String,Integer>();
        if(result>0){
            map.put("status",200);
        }
        else{
            map.put("status",500);
        }
        return map;
    }

    //广告内容编辑更新
    @RequestMapping("/rest/content/edit")
    public Map<String,Integer> update(Content content){
        int result = contentService.update(content);
        //返回状态码，告知更新是否成功
        Map<String,Integer> map=new HashMap<String,Integer>();
        if(result>0){
            map.put("status",200);
        }
        else{
            map.put("status",500);
        }
        return map;

    }

}
