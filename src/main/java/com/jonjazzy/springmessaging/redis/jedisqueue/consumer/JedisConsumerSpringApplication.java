package com.jonjazzy.springmessaging.redis.jedisqueue.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
@EnableScheduling
public class JedisConsumerSpringApplication
{
    private static Logger LOGGER = LoggerFactory.getLogger(JedisConsumerSpringApplication.class);

    //Main Application
    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(JedisConsumerSpringApplication.class, args);
//        Producer.produceMessages();
        LOGGER.info("We here breh!");
    }
}
