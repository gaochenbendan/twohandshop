package com.orange.admin.schedule;

import com.orange.admin.service.adminservice.DatabaseDakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 数据库备份定时器任务
 * @author 高晨
 */
@Configuration
@EnableScheduling
public class BackUpSchedule {


    @Autowired
    private DatabaseDakService databaseDakService;

    /**
     * 延迟10秒 后每5秒执行一次
     *  @Scheduled(initialDelay=10000,fixedRate = 5000)
     *  每天 1 点 2分  3秒
     *  @Scheduled(cron = "3 2 1 * * ?")
     */
    @Scheduled(cron = "0 0 1 * * ?")
    private void backUp(){
       databaseDakService.backUp();
    }


}
