package com.itxj.Utils;

import com.itxj.service.TestService;

import java.io.IOException;
import java.util.List;

import static com.itxj.Utils.ResolveHtml.ResolveHtml;

/*
 *  @项目名：  GetPageContent
 *  @包名：    com.itxj.Utils
 *  @文件名:   GrabByUrl
 *  @创建者:   小吉
 *  @创建时间:  2018/11/21 22:40
 *  @描述：    TODO
 */
public class GrabByUrl {

    //
    public String grab(TestService testService, String urlPre, String urlPost, int max, int min) throws IOException {
        //地址
        String url = "";
        //-----------------------------------------------------------------------------
        for (int i = min; i < max; i++) {
            url = urlPre + i + urlPost;
            //http连接页面获取html代码
            String html = new GetHtml().getHtml(url,0);
            if (html == "" || html.equals("")) {
                System.out.println("空的==");
                continue;
            }

            System.out.println("第"+i+"页=="+url);
            //System.out.println(html);
            //解析html代码list
            List list = ResolveHtml(html);
            //添加到数据库
            testService.insert(list);


        }

        return "success";
    }

}