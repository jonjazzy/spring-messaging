package com.jonjazzy.springmessaging.rabbitmq.base.receiver;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
    Create a receiver that responds to published messages

    The Receiver is a POJO that defines a method for receiving messages.
    When you register it to receive messages, you can name it anything you want.
*/
@Component
public class Receiver {

    private Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");
    }
}
