package com.jonjazzy.springmessaging.redis.jedisqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
@EnableScheduling
public class JedisSpringApplication
{
    private static Logger LOGGER = LoggerFactory.getLogger(JedisSpringApplication.class);

    //Main Application
    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(JedisSpringApplication.class, args);
//        Producer.produceMessages();
        LOGGER.info("We here breh!");
    }
}
