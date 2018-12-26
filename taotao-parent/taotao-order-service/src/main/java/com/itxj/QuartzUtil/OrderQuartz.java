package com.itxj.QuartzUtil;

import com.itxj.service.OrderService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.QuartzUtil
 *  @文件名:   orderQuartz
 *  @创建者:   小吉
 *  @创建时间:  2018/12/26 13:59
 *  @描述：    JobDetail  任务描述  Trigger 任务触发器    Scheduler 核心调度器
 */
@Component
public class OrderQuartz  {

    @Autowired
    private OrderService orderService;
    //任务描述
    @Bean
    public JobDetail orderJobDetail(){
        JobDataMap jobDataMap=new JobDataMap();
        jobDataMap.put("os",orderService);

        return JobBuilder
                .newJob(OrderQuartzJob.class)//调用任务
                .setJobData(jobDataMap)//将orderService 传给Job
               .withIdentity("clearOrder","order")//任务描述---标签---分组
                .build();
    }

    //任务触发器
    @Bean
    public Trigger orderTrigger(){
ScheduleBuilder scheduleBuilder=CronScheduleBuilder.cronSchedule("0/5 * * * * ?");//CronScheduleBuilder的表达式配置
        return TriggerBuilder
                .newTrigger()
                .withSchedule(scheduleBuilder)//
                .build();
    }

    //核心调度器
    @Bean
    public Scheduler orderScheduler() throws SchedulerException {

       return StdSchedulerFactory.getDefaultScheduler();//返回任务调度器
    }

}
