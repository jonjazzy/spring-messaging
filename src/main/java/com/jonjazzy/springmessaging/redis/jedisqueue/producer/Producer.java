package com.jonjazzy.springmessaging.redis.jedisqueue.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class Producer
{
    private static Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Scheduled(fixedRate = 3000)
    public static void produceMessages()
    {
        Jedis jedis = new Jedis("localhost");
        int randomKeyValue = 1 + (int)(Math.random() * 15);

        /*
            Lets start by publishing messages to the mq-key queue using the rpush() method.
            Which will add a message to the end of the list.
        */
        LOGGER.info("Pushing messages...");
        jedis.rpush("mq-key", "first message:- " + randomKeyValue);
        LOGGER.info("key --> mq-key | value --> {}", randomKeyValue);
//        jedis.rpush("mq-key", "second message:- " + (1 + (int)(Math.random() * 15)));
//        jedis.rpush("mq-key", "third message:- " + (1 + (int)(Math.random() * 15)));
        LOGGER.info("Done pushing messages...");
    }

}
