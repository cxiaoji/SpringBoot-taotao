package com.itxj.controller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.controller
 *  @文件名:   ItemController
 *  @创建者:   小吉
 *  @创建时间:  2018/10/30 19:05
 *  @描述：    httpclient请求测试
 */
public class ItemController {

//测试查询商品请求
    @Test
    public void testGet() throws Exception {

        CloseableHttpClient httpClient= HttpClients.createDefault();//创建一个默认的http请求
        String url="http://localhost:8084/item/536563";

        HttpGet httpGet=new HttpGet(url);//请求地址

        CloseableHttpResponse response = httpClient.execute(httpGet);//发起请求

        HttpEntity entity = response.getEntity();//获取请求的实体类

        String content = EntityUtils.toString(entity,"utf-8");//使用EntityUtils编码实体类

        System.out.println("content=" + content);

        httpClient.close();//关闭请求http资源

    }
//测试添加商品请求
    @Test
    public void testPost() throws IOException{

        //创建请求
        CloseableHttpClient httpClient=HttpClients.createDefault();

        //构建请求地址url
        String url="http://localhost:8084/item";

        //构建请求方式
        HttpPost httpPost=new HttpPost(url);

        //Post请求添加请求参数
        List<NameValuePair> params=new ArrayList<>();
        params.add(new BasicNameValuePair("title","xxj"));
        params.add(new BasicNameValuePair("sellPoint","好看"));
        params.add(new BasicNameValuePair("price","2588"));
        params.add(new BasicNameValuePair("num","9999"));
        params.add(new BasicNameValuePair("cid","76"));

        //编码请求参数
        HttpEntity entity=new UrlEncodedFormEntity(params,"utf-8");

        //添加请求参数
        httpPost.setEntity(entity);

        //执行请求
        httpClient.execute(httpPost);

        //关闭请求
        httpClient.close();
    }

//测试删除商品请求
    @Test
    public void testDelete() throws Exception{

        CloseableHttpClient httpClient=HttpClients.createDefault();
        String url="http://localhost:8084/item/1540947827883";
        HttpDelete httpDelete=new HttpDelete(url);
        HttpResponse response = httpClient.execute(httpDelete);
        HttpEntity entity = response.getEntity();
        EntityUtils.toString(entity,"utf-8");
        httpClient.close();

    }
//测试更新商品请求
    @Test
    public void testPut() throws Exception{

        CloseableHttpClient httpClient=HttpClients.createDefault();

        String url="http://localhost:8084/item";

        HttpPut httpPut=new HttpPut(url);

        List<NameValuePair> param=new ArrayList<>();
        param.add(new BasicNameValuePair("id","536563"));
        param.add(new BasicNameValuePair("title","小吉好帅"));

        HttpEntity entity=new UrlEncodedFormEntity(param,"utf-8");

        httpPut.setEntity(entity);

        httpClient.execute(httpPut);

        httpClient.close();
    }
}
