package com.itxj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   CartMergeService
 *  @创建者:   小吉
 *  @创建时间:  2018/12/19 18:33
 *  @描述：    TODO
 */
public interface CartMergeService {

    void mergeCart(HttpServletRequest request, HttpServletResponse response, long userId);
}
