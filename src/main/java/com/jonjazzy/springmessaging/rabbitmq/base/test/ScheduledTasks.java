package com.jonjazzy.springmessaging.rabbitmq.base.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /*
     *   The Scheduled annotation defines when a particular method runs.
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("JonJazzy:SchedulledTasks:- {}", dateFormat.format(new Date()));
    }
}
