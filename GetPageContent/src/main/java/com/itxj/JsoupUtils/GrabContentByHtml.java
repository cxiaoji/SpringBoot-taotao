package com.itxj.JsoupUtils;

import com.itxj.pojo.Test;
import com.itxj.service.TestService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/*
 *  @项目名：  GetPageContent
 *  @包名：    com.itxj.JsoupUtils
 *  @文件名:   GrabContentByHtml
 *  @创建者:   小吉
 *  @创建时间:  2018/12/21 14:30
 *  @描述：    TODO
 */
@Service
public class GrabContentByHtml {
    @Autowired
    private TestService testService;

    public  void grabContentByhtml(String urlPre, String urlPost, int max, int min) throws IOException {
        String url="";
        for (int i=min;i<max;i++){
            url=urlPre+i+urlPost;
           // System.out.println(url);

            Document document = GetDocumentByUrl.getDocumentByUrl(url);
            Elements listbody = document.getElementsByClass("channellist box mb bg");
            Elements lis = listbody.get(0).getElementsByTag("li");


        for (Element li : lis) {
            Elements a = li.getElementsByTag("a");
            String[] split = li.getElementsByTag("p").get(1).text().split("人气：");
            String s = split[1];
            //System.out.println(s);
            long host = Long.parseLong(s);


            String text = a.text();
            String urls = "http://www.868xx.com"+a.attr("href");
            //if(text.contains("摄")){
if (host>=1000){
    System.out.println(text+"..."+urls+"...."+host);
    //}
    Test test=new Test();
    test.setName(text);
    test.setUrl(urls);
    testService.add(test);
}



        }


        }



    }
}
