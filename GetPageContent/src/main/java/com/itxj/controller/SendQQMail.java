package com.itxj.controller;

import com.itxj.service.TestService;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Random;

@RestController
public class SendQQMail {

    @Autowired
    private static TestService testService;

    public static void main(String[] args) {
        try {
            // TestService testService=new TestImpl();

            String QQ = null;
            int min = (int) Math.pow(10, 7);
            int max = (int) (Math.pow(10, 8) - 1);
            Random r = new Random();

            for (int i = 0; i < 1000; i++) {
                //随机生成QQ号码
                QQ = "15"+(r.nextInt(max) % (max - min + 1) + min + "")+"@qq.com";
                System.out.println("QQ==" + QQ);

                //判断QQ号码是否发送过-----查询数据库是否存在
                //if(!StringUtils.isEmpty(testService.selecQQ(QQ))){
                //如果存在跳出循环。
                //  continue;
                //  }
                // 不存在则发送邮件且插入数据库
                //QQ="193936707";
                Boolean b = sendMail(QQ, "好朋友，好久不见！！！", "支付宝口令红包 \n" +
                        "\n" +
                        "\n" +
                        "打开支付宝首页搜“515196443”领红包，领到大红包的小伙伴赶紧使用哦！\n" +
                        "\n" +
                        "\n" +
                        "打扰了，还请见谅\n");
                System.out.println("邮件发送成功"+b+"............");
                System.out.println("============================================");

                if(!b){
                    continue;
                }

                //testService.saveQQ(QQ);
                Thread.sleep(10000);
            }
            System.out.println("全部发送完毕");

        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
            System.out.println("邮件发送失败............");

        }
    }


    //新建一个Java 测试类 ：在类中直接调用一下方法
     /*
         MailUtils.sendMail(String ReceMailUser, String subject,String emailMsg);
                                收邮件的账号         邮件的主题     邮件的信息
     */

    public static Boolean sendMail(String ReceMailUser, String subject, String emailMsg) throws GeneralSecurityException, MessagingException {
        Properties props = new Properties();

        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        //开启了 SSL 加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getInstance(props);

        // 2.创建一个Message，它相当于是邮件内容
        Message msg = new MimeMessage(session);
        msg.setSubject("seenews 错误");

//	    StringBuilder builder = new StringBuilder();
//	    builder.append("url = " + "http://blog.csdn.net/never_cxb/article/details/50524571");
//	    builder.append("\n页面爬虫错误");
//	    //builder.append("\n时间 " + TimeTool.getCurrentTime());
//	    msg.setText(builder.toString());
//
        msg.setFrom(new InternetAddress("827693164@qq.com"));// 设置发送者

        msg.setSubject(subject);
        // message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

        msg.setContent(emailMsg, "text/html;charset=utf-8");

        // 3.创建 Transport用于将邮件发送
        Transport transport = session.getTransport();

        try {
            transport.connect("smtp.qq.com", "827693164@qq.com", "jjvzqtedqivdbcgb");//设置验证发送人和授权码

            transport.sendMessage(msg,
                    new Address[]{
                            new InternetAddress(ReceMailUser)
                    }
            );// 发送信息与接收者


            transport.close();

            return true;
        } catch (Exception e){
            return false;
        }



    }


}

