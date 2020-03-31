package com.jonjazzy.springmessaging.rabbitmq.base.producer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Producer
{

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*
     *   The Scheduled annotation defines when a particular method runs.
     */
    @Scheduled(fixedRate = 3000)
    public void reportCurrentTime()
    {
        LOGGER.info("JonJazzy:SchedulledTasks:- {}", dateFormat.format(new Date()));
        RabbitTemplate rbt = getRabbitTemplate();
        rbt.convertAndSend(ProducerRabbitmqApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!" + dateFormat.format(new Date()));
    }

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }
}