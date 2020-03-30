package com.jonjazzy.springmessaging.redis.base.subscriber;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

/*
 *  The Receiver is a POJO that defines a method for receiving messages.
 *  When you register the Receiver as a message listener,
 *  you can name the message-handling method whatever you want.
 */
public class Receiver
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private AtomicInteger counter = new AtomicInteger();

    @Autowired
    ApplicationContext appContext;

    public void receiveMessage(String message) {
        LOGGER.info("Received Message:--> <" + message + ">");
        counter.incrementAndGet();
    }

    public int messageHandlingMethod() {
        //return count of messages received.
        return counter.get();
    }

    @Scheduled(fixedDelay = 300L)
    public void subscriberService()
    {
        Receiver receiver = appContext.getBean(Receiver.class);
        LOGGER.info("Receiving message. Message Count:- " + receiver.messageHandlingMethod());
    }
}