package com.itxj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itxj.pojo.Item;
import com.itxj.service.ItemServive;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.controller
 *  @文件名:   ItemController
 *  @创建者:   小吉
 *  @创建时间:  2018/9/26 9:52
 *  @描述：    商品新增
 */
@Controller
public class ItemController {

    @Reference
    private ItemServive itemService;

    //添加商品
    //@RequestMapping("/rest/item")
    @PostMapping("/rest/item")
    @ResponseBody
    public String  addItem(Item item,String desc){
        Integer result = itemService.addItem(item, desc);

        System.out.println("result=="+result);
        return "success";
    }

    //查询商品列表。。。。。。page=1&rows=30
    @GetMapping("/rest/item")
    @ResponseBody
    public Map<String,Object>  listItem(int page,int rows){

        PageInfo<Item> itemPageInfo = itemService.listItem(page, rows);
        //easyui 的数据表格显示数据，要求有固定格式---要求有total和rows属性
        //total 属性 ----总共有多少条数据
        //rows 属性------当前页集合数据

        Map<String,Object> map=new HashMap<>();
        map.put("total",itemPageInfo.getTotal());
        map.put("rows",itemPageInfo.getList());

        return map;
    }
}
