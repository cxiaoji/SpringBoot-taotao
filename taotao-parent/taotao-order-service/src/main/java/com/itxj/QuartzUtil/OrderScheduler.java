package com.itxj.QuartzUtil;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.QuartzUtil
 *  @文件名:   orderScheduler
 *  @创建者:   小吉
 *  @创建时间:  2018/12/26 14:19
 *  @描述：    TODO
 */
@Component("os")
public class OrderScheduler {

    @Autowired
    private Scheduler orderScheduler;

    @Autowired
    private JobDetail orderJobDetail;

    @Autowired
    private Trigger orderTrigger;

    public void startJob() throws SchedulerException {
        orderScheduler.scheduleJob(orderJobDetail,orderTrigger);
        orderScheduler.start();//开启任务
    }
    public void pauseJob(){

    } public void deleteJob(){

    }

}
