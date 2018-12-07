package com.itxj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itxj.pojo.Item;
import com.itxj.service.ItemServive;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.controller
 *  @文件名:   ItemController
 *  @创建者:   小吉
 *  @创建时间:  2018/12/3 16:24
 *  @描述：    TODO
 */
@Controller
public class ItemController {


    @Reference
    private ItemServive itemServive;

    @RequestMapping("/item/{id}")
    //@ResponseBody
    public String item(@PathVariable String id, Model model){

        Item item = itemServive.getItemById(Long.parseLong(id));
        System.out.println(item.getTitle());
        model.addAttribute("item",item);
        return "item";
    }

}
