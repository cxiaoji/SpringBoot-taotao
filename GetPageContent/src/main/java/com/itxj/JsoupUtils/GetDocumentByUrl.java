package com.itxj.JsoupUtils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/*
 *  @项目名：  GetPageContent
 *  @包名：    com.itxj.JsoupUtils
 *  @文件名:   GetDocumentByUrl
 *  @创建者:   小吉
 *  @创建时间:  2018/12/21 14:26
 *  @描述：    TODO
 */
public class GetDocumentByUrl {

    public static Document getDocumentByUrl(String url) throws IOException {

        //获取连接
        Connection connect = Jsoup.connect(url);

        //设置连接超时
        connect.timeout(10000);

        //获取document文件
        Document document = connect.get();

        //转换Html字符串页面


        return document;
    }
}
