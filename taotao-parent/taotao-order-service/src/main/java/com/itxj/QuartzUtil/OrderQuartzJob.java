package com.itxj.QuartzUtil;

import com.itxj.service.OrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.QuartzUtil
 *  @文件名:   orderQuartzJob
 *  @创建者:   小吉
 *  @创建时间:  2018/12/26 13:59
 *  @描述：    TODO
 */


public class OrderQuartzJob implements Job {

//    @Autowired
//    private OrderService orderService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        orderService.clearOrder();
        OrderService os = (OrderService) context.getJobDetail().getJobDataMap().get("os");
        System.out.println("orderService==="+os);

        os.clearOrder();
    }
}
