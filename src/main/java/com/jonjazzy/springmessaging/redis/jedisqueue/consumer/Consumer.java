package com.jonjazzy.springmessaging.redis.jedisqueue.consumer;

import com.jonjazzy.springmessaging.redis.jedisqueue.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import java.util.List;

@Component
public class Consumer
{
    private static final int TIMEOUT = 0;
    private Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Scheduled(fixedRate = 1500)
    public void consumeMessages()
    {
        Jedis jedis = new Jedis("localhost");

        LOGGER.info("Waiting for a message in the queue");
        List<String> messages = jedis.blpop(TIMEOUT, "mq-key");
        LOGGER.info("received message with key:" + messages.get(0) + " with value:" + messages.get(1));
    }
}
