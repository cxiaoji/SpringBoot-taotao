package com.itxj.QuartzUtil;

import org.quartz.SchedulerException;
import org.springframework.web.context.ContextLoaderListener;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.QuartzUtil
 *  @文件名:   orderListener
 *  @创建者:   小吉
 *  @创建时间:  2018/12/26 14:36
 *  @描述：    TODO
 */
//@Component
public class ContextLoaderListenerByOrder extends ContextLoaderListener {

    @Resource(name="os")
    private OrderScheduler  os;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("清除订单正在进行。。。。。。。");
        try {
            os.startJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        System.out.println("清除订单结束。。。。。。。");

    }
}
