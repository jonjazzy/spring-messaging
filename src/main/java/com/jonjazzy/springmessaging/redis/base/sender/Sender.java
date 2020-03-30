package com.jonjazzy.springmessaging.redis.base.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private ApplicationContext appContext;

    /*
     *   The Scheduled annotation defines when a particular method runs.
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws InterruptedException {
        LOGGER.info("Sending transaction...");
        StringRedisTemplate template = appContext.getBean(StringRedisTemplate.class);
        template.convertAndSend("transaction", "Transaction --> " + dateFormat.format(new Date()));
        LOGGER.info("Message sent! Sleeping thread \uD83D\uDE01");
    }
}
