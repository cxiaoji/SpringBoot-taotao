package com.itxj.Utils;

import com.itxj.pojo.Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  GetPageContent
 *  @包名：    com.itxj.Utils
 *  @文件名:   ResolveHtml
 *  @创建者:   小吉
 *  @创建时间:  2018/11/21 10:43
 *  @描述：    //解析html代码
 */
public class ResolveHtml {

    public static List<Test> ResolveHtml(String html) {

        //创建集合接受数据
        List<Test> list = new ArrayList<>();
        //解析html为doc文档
        Document doc = Jsoup.parse(html);
        //根据class获取标签div
        Elements elementsByClass = doc.getElementsByClass("listbody");
        Elements tables = elementsByClass.get(0).getElementsByTag("table");

        System.out.println(tables.get(0).getElementsByTag("tr").get(0).getElementsByClass("ltd").text());

        //Elements ul = elementsByClass.get(0).getElementsByTag("ul");
//        Elements lis = ul.get(0).getElementsByTag("li");
//        System.out.println("llllll");
//        //----------------------------------------------------------------------------------------------------
//        for (Element li : lis) {
//            Test t = new Test();//创建对象存储数据
//
//            String name = li.getElementsByTag("img").attr("alt");
//            String url = li.getElementsByTag("a").attr("href");
//            String substring = url.substring(url.indexOf("/?"), url.indexOf(".html"));
//            url = "http://25c6.com/video" + substring + "-0-0.html";
//            if (name.contains("拍") || name.contains("摄") ){
//                t.setName(name);
//                t.setUrl(url);
//                t.setNum(0);
//                list.add(t);
//            }
//            //-----------------------------------------------------------------------------
//        }
        //----------------------------------------------------------------------------------------------------------------------------------
        return list;
    }

    public static void main(String[] args) throws IOException {
        String html = new GetHtml().getHtml("http://www.quanben9.com/duanpian/2",0);
        //System.out.println(html);
        ResolveHtml(html);
}
}
