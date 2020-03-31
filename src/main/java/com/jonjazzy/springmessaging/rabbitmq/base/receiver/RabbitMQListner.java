package com.jonjazzy.springmessaging.rabbitmq.base.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListner implements MessageListener
{
    public void onMessage(Message message) {
        System.out.println("Consuming Message - " + new String(message.getBody()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
