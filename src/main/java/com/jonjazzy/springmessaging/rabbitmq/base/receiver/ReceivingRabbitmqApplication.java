package com.jonjazzy.springmessaging.rabbitmq.base.receiver;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.aop.TargetSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReceivingRabbitmqApplication
{
    static final String topicExchangeName = "spring-boot-exchange";
    static final String queueName = "spring-boot";

    @Bean
    /*  Configure/register a Receiver with the message listener container to reeive messages */
    /*  The connectionFactory drives both, letting them connect to the RabbitMQ server.    */
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapter) {
                                             RabbitMQListner listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

//    @Bean
//    /*
//        The bean defined in the listenerAdapter() method below, i.e. Receiver, is registered as a message listener
//        in the container (defined in container() --> container.setMessageListener(listenerAdapter))
//
//        It listens for messages on the spring-boot queue.
//
//        Because the Receiver class is a POJO, it needs to be wrapped in the MessageListenerAdapter,
//        where you specify that it invokes receiveMessage.
//    */
//    MessageListenerAdapter listenerAdapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }

    public static void main(String[] args){
        SpringApplication.run(ReceivingRabbitmqApplication.class, args).close();
    }
}