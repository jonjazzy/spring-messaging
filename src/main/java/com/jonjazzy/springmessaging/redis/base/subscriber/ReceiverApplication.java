package com.jonjazzy.springmessaging.redis.base.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
@EnableScheduling
public class ReceiverApplication
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverApplication.class);

    //Main Application
    public static void main(String[] args) throws InterruptedException
    {

        SpringApplication.run(ReceiverApplication.class, args);

        /*
            The main() method kicks off everything by creating a Spring application context.
            The application context then starts the message listener container,
            and the message listener container bean starts listening for messages.
        */
//        ApplicationContext ctx = SpringApplication.run(ReceiverApplication.class, args);
//        Receiver receiver = ctx.getBean(Receiver.class);
//
//        while (receiver.messageHandlingMethod() == 0)
//        {
//            LOGGER.info("Receiving message..." + receiver.messageHandlingMethod());
//            Thread.sleep(500L);
//        }
    }


    /*
     *      Because the Receiver class is a POJO, it needs to be wrapped in a MessageListenerAdapter
     *      that implements the MessageListener interface (which is required by addMessageListener()).
     *      The MessageListenerAdapter is also configured to call the Receiver.receiveMessage() method on
     *      Receiver when a message arrives.
     */
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    Receiver receiver() {
        return new Receiver();
    }

    //  message listener container
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter)
    {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        //listenerAdapter method is registered as a message listener in the message listener container (container)
        //listenerAdapter will listen for messages on the topic "transaction"
        container.addMessageListener(listenerAdapter, new PatternTopic("transaction"));

        return container;
    }
}
