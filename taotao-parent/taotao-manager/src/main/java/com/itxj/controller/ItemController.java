package com.itxj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itxj.pojo.Item;
import com.itxj.service.ItemServive;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String addItem(Item item, String desc) {
        Integer result = itemService.addItem(item, desc);

        System.out.println("result==" + result);
        return "success";
    }

    //查询商品列表。。。。。。page=1&rows=30
    @GetMapping("/rest/item")
    @ResponseBody
    public Map<String, Object> listItem(int page, int rows) {

        PageInfo<Item> itemPageInfo = itemService.listItem(page, rows);
        //easyui 的数据表格显示数据，要求有固定格式---要求有total和rows属性
        //total 属性 ----总共有多少条数据
        //rows 属性------当前页集合数据

        Map<String, Object> map = new HashMap<>();
        map.put("total", itemPageInfo.getTotal());
        map.put("rows", itemPageInfo.getList());

        return map;
    }

    //对商品进行---删除/上架/下架
    @RequestMapping("/rest/item/{action}")
    @ResponseBody
    public Map<String, Integer> deleteAndInstockAndReshlfItemByItem(@PathVariable String action, String ids) {
        System.out.println("action===" + action);
        System.out.println("ids===" + ids);


        //对商品进行---删除/上架/下架
        Integer result = itemService.deleteAndInstockAndReshlfItemByItem(action, ids);

        //判断操作结果result>0----成功--返回 状态码200；反之返回状态码500
        Map<String, Integer> map = new HashMap<>();

        if (result > 0) {
            map.put("status", 200);

        } else {
            map.put("status", 500);

        }

        return map;
    }

    //对商品进行编辑
    //对商品进行---删除/上架/下架
    @RequestMapping("/rest/item/update")
    @ResponseBody
    public String updateItem(Item item) {

        //1.调用接口实现商品的编辑修改

        Integer result = itemService.updateItem(item);


            return "success";





    }


}
