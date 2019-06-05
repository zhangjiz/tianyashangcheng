package com.zcf.world.common.config.quartz;//package com.zcf.world.common.config.quartz;
//
//import com.zcf.world.quartz.MyJob;
//import com.zcf.world.quartz.MyJobOne;
//import org.quartz.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class QuartzConfiguration {
//
//    // 使用jobDetail包装job
//    @Bean
//    public JobDetail myJobDetail() {
//        return JobBuilder.newJob(MyJob.class).withIdentity("myJob").storeDurably().build();
//    }
//
//    // 把jobDetail注册到trigger上去
//    @Bean
//    public Trigger myJobTrigger() {
//        SimpleScheduleBuilder scheduleBuilder=SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(5).repeatForever();
//        return TriggerBuilder.newTrigger()
//                .forJob(myJobDetail())
//                .withIdentity("myJobTrigger")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
//    // 使用jobDetail包装job
//    @Bean
//    public JobDetail myJobDetail1() {
//        return JobBuilder.newJob(MyJobOne.class).withIdentity("myJob1").storeDurably().build();
//    }
//
//    // 把jobDetail注册到trigger上去
//    @Bean
//    public Trigger myJobTrigger1() {
//        SimpleScheduleBuilder scheduleBuilder=SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(5).repeatForever();
//        return TriggerBuilder.newTrigger()
//                .forJob(myJobDetail1())
//                .withIdentity("myJobTrigger1")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
//}
