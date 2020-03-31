package com.jonjazzy.springmessaging.rabbitmq.base.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling   //The @EnableScheduling annotation ensures that a background task executor is created. Without it, nothing gets scheduled.
public class SpringSchedullingTasksApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(SpringSchedullingTasksApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringSchedullingTasksApplication.class, args);

        LOGGER.info("spring-schedulling-tasks");
        LOGGER.info("---------------------------------------");
    }

}
