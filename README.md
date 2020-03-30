# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

#### INstalling Redis on Windows
https://stackoverflow.com/questions/6476945/how-do-i-run-redis-on-windows/20200022#20200022
https://www.youtube.com/watch?time_continue=73&v=ncFhlv-gBXQ&feature=emb_logo&ab_channel=AutomationStepbyStep-RaghavPal
https://www.c-sharpcorner.com/article/installing-redis-cache-on-windows/
https://github.com/microsoftarchive/redis/releases (installer)
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/maven-plugin/)
* [Spring Data Redis (Access+Driver)](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-redis)

#### Redis configuration
https://redis.io/topics/config

#### Redis.windows.conf vs Redis.windows-service.conf for Windows: 
https://stackoverflow.com/questions/56920798/redis-windows-conf-vs-redis-windows-service-conf

#### About MEssaging
https://dzone.com/articles/introduction-to-message-brokers-part-1-apache-kafk



### Redis Message Receiver
In any messaging-based application, there are message publishers and messaging receivers. To create the message receiver, implement a receiver with a method to respond to messages
```$xslt
package com.jonjazzy.springmessaging.redis.base;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 *  The Receiver is a POJO that defines a method for receiving messages.
 *  When you register the Receiver as a message listener,
 *  you can name the message-handling method whatever you want.
 */
public class Receiver
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private AtomicInteger counter = new AtomicInteger();

    public void receiveMessage(String message) {
        LOGGER.info("Received Message:--> <" + message + ">");
        counter.incrementAndGet();
    }

    public int messageHandlingMethod() {
        //return count of messages received.
        return counter.get();
    }
}
```

The **Receiver** is a POJO that defines a method for receiving messages. When you register the Receiver as a message listener, 
you can name the message-handling method whatever you want.

### Register the Listener and Send a Message
Spring Data Redis provides all the components you need to send and receive messages with Redis. Specifically, you need to configure:

- A connection factory
- A message listener container
- A Redis template

The connection factory and message listener container beans are all you need to listen for messages. 
**To send a message**, you also need a Redis template. Here, it is a bean configured as a StringRedisTemplate, an implementation of RedisTemplate that is focused on the common use of Redis, where both keys and values are String instances.

