package com.itxj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itxj.pojo.Item;
import com.itxj.service.ItemServive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.controller
 *  @文件名:   ItemInterfaceController
 *  @创建者:   小吉
 *  @创建时间:  2018/10/30 18:44
 *  @描述：     restful风格设计
 */
@Controller
public class ItemInterfaceController {

    @Reference
    private ItemServive itemServive;

//通过id查询商品
    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id){

        try {
            Item itemById = itemServive.getItemById(id);
            //ResponseEntity.ok(itemById);
            //没有问题则执行此语句，返回数据
            return ResponseEntity.status(HttpStatus.OK).body(itemById);
        } catch (Exception e) {
            e.printStackTrace();
            //有问题则执行此语句
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }

    }

//添加商品
    @PostMapping("/item")
    public ResponseEntity<Void> addItem(Item item,String desc){
        try {
            Integer integer = itemServive.addItem(item, desc);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

//通过id删除商品
    @DeleteMapping("/item/{id}")
    public ResponseEntity<Void> delItem(@PathVariable long id){
        try {
            Integer integer = itemServive.delItem(id);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

//通过id更新商品
    @PutMapping("/item")
    public ResponseEntity<Void> updateItem(Item item){
        try {
            Integer integer = itemServive.updateItem(item);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
