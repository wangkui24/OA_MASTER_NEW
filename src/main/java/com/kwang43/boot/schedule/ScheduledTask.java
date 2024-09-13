package com.kwang43.boot.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//使用@Component将这个定时任务类注册成一个bean组件，交给Spring容器管理
public class ScheduledTask {
    /*
    {秒} {分} {时} {日期（具体哪天）} {月} {星期}
    秒：必填项，允许的值范围是0-59，支持的特殊符号包括, - * /，,表示特定的某一秒才会触发任务，-表示一段时间内会触发任务，*表示每一秒都会触发，/表示从哪一个时刻开始，每隔多长时间触发一次任
    分：必填项，允许的值范围是0-59，支持的特殊符号和秒一样，含义类推
    时：必填项，允许的值范围是0-23，支持的特殊符号和秒一样，含义类推
    日期：必填项，允许的值范围是1-31，支持的特殊符号相比秒多了?，表示与{星期}互斥，即意味着若明确指定{星期}触发，则表示{日期}无意义，以免引起冲突和混乱
    月：必填项，允许的值范围是1-12（JAN-DEC），支持的特殊符号与秒一样，含义类推
    星期：必填项，允许值范围是1~7 (SUN-SAT),1代表星期天（一星期的第一天），以此类推，7代表星期六，支持的符号相比秒多了?，表达的含义是与{日期}互斥，即意味着若明确指定{日期}触发，则表示{星期}无意义
    */
//    @Scheduled(cron = "*/1 * * * * ?")
//    public void test1(){
//        log.info("这个定时任务1----");
//    }
}
