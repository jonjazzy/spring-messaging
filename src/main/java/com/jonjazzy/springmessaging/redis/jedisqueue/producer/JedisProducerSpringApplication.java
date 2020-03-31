package com.jonjazzy.springmessaging.redis.jedisqueue.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
@EnableScheduling
public class JedisProducerSpringApplication
{
    private static Logger LOGGER = LoggerFactory.getLogger(JedisProducerSpringApplication.class);

    //Main Application
    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(JedisProducerSpringApplication.class, args);
//        Producer.produceMessages();
        LOGGER.info("We here breh!");
    }
}
