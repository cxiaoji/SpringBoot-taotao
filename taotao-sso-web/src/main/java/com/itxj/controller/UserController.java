package com.itxj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itxj.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.controller
 *  @文件名:   UserController
 *  @创建者:   小吉
 *  @创建时间:  2018/10/31 16:17
 *  @描述：    TODO
 */
@Controller
public class UserController {

    @Reference
    private UserService userService;

    @GetMapping("/user/check/{param}/{type}")
  public ResponseEntity<String> check(@PathVariable String param,@PathVariable int type,String callback){

        try {
            Boolean check = userService.check(param, type);
            //callback=jsonp1541218052306
            String result="";
            if(!StringUtils.isEmpty(callback)){
                result=callback+"("+check+")";
            }
            else{
                result=check+"";
            }
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}
