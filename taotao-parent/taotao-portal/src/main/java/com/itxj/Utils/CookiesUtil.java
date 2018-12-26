package com.itxj.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.Utils
 *  @文件名:   CookiesUtils
 *  @创建者:   小吉
 *  @创建时间:  2018/12/17 15:31
 *  @描述：    TODO
 */
public  class CookiesUtil {
  public static String  getTicketByCookies(HttpServletRequest request){

      //循环遍历Cookeis获取用户登录凭证ticket
      String ticket="";
      Cookie[] cookies = request.getCookies();
      if(cookies!=null){
          for (Cookie cookie : cookies) {
              String cookieName = cookie.getName();
              // System.out.println("cookie===" + cookieName + "\n");
              if(cookieName.equals("ticket"))
              {
                  ticket = cookie.getValue();
                  System.out.println("用户登录凭证ticket==" + ticket);
                  break;
              }
          }
      }

      return ticket;
  }
}
