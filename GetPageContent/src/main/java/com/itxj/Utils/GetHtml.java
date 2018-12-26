package com.itxj.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/*
 *  @项目名：  GetPageContent
 *  @包名：    com.itxj.Utils
 *  @文件名:   GetHtml
 *  @创建者:   小吉
 *  @创建时间:  2018/11/21 10:41
 *  @描述：    TODO
 */
public class GetHtml {



    //http连接页面获取html代码           num代表访问次数
    public  String getHtml(String url,int num) throws IOException {
        CloseableHttpClient httpClient= HttpClients.createDefault();//创建一个默认的http请求
        HttpGet httpGet=new HttpGet(url);//请求地址
        //加入超时设置
        RequestConfig config=RequestConfig.custom()
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .build();
        httpGet.setConfig(config);

        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");//设置代理信息
        CloseableHttpResponse response =null;
        int i=0;
          response = httpClient.execute(httpGet);//发起请求
            System.out.println("状态："+response.getStatusLine().getStatusCode());


        if(response.getStatusLine().getStatusCode()==200){


            HttpEntity entity = response.getEntity();//获取请求的实体类

           String html = EntityUtils.toString(entity,"utf-8");//使用EntityUtils编码实体类---获取页面的html代码
            //System.out.println("content=" + content);

            httpClient.close();//关闭请求http资源
            return html;
        }
        else if(response.getStatusLine().getStatusCode()!=200&&(num==0||num==1)){
            num++;
            System.out.println("第几次访问：" + num);
            String html = getHtml(url, num);
            System.out.println("页面" + html);
            return html;
        }
          else{
            System.out.println("我访问了两次不可以" );
            return "";
        }



    }


//    }
}