# Getting Started

## Redis
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

## Message Queue with Jedis
Jedis - https://www.baeldung.com/jedis-java-redis-client-library
Simple Redis Message Queue - https://memorynotfound.com/simple-redis-message-queue/


## Redis

### Reference Documentation
For further reference, please consider the following sections:
* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)
* [Installing on Windows](https://www.rabbitmq.com/install-windows.html#installer)
* [Installing on Windows](https://www.rabbitmq.com/install-windows.html#installer)
* [Configuration](https://www.rabbitmq.com/configure.html#customise-windows-environment)
* [Command Line Tools](https://www.rabbitmq.com/cli.html)

### Create a RabbitMQ Message Receiver
With any messaging-based application, you need to create a receiver that responds to published messages. 
The following listing (from Receiver.java) shows how to do so:

```
package com.example.messagingrabbitmq;

import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(String message) {
    System.out.println("Received <" + message + ">");
    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}
```
The **Receiver** is a POJO that defines a method for receiving messages. When you register it to receive messages, you can name it anything you want.

```
For convenience, this POJO also has a CountDownLatch. This lets it signal that the message has been received. This is something you are not likely to implement in a production application.
```

### Register the Listener and Send a Message
Spring AMQP’s RabbitTemplate provides everything you need to send and receive messages with RabbitMQ. 
However, you need to:
- Configure a message listener container.
- Declare the **queue**, the **exchange**, and the **binding** between them.
- Configure a component to send some messages to test the listener.

```
Spring Boot automatically creates a connection factory and a RabbitTemplate, reducing the amount of code you have to write.
```

You will use RabbitTemplate to send messages, and you will register a Receiver with the message listener container to receive messages. 
The connection factory drives both, letting them connect to the RabbitMQ server. 
The following listing (from MessagingRabbitApplication.java) shows how to create the application class:

```
package com.example.messagingrabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessagingRabbitmqApplication {

  static final String topicExchangeName = "spring-boot-exchange";

  static final String queueName = "spring-boot";

  @Bean
  Queue queue() {
    return new Queue(queueName, false);
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange(topicExchangeName);
  }

  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
  }

  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName);
    container.setMessageListener(listenerAdapter);
    return container;
  }

  @Bean
  MessageListenerAdapter listenerAdapter(Receiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(MessagingRabbitmqApplication.class, args).close();
  }

}
```

@SpringBootApplication is a convenience annotation that adds all of the following:
- @Configuration: Tags the class as a source of bean definitions for the application context.
- @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.
- @ComponentScan: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.

The main() method uses Spring Boot’s SpringApplication.run() method to launch an application. 
Did you notice that there was not a single line of XML? There is no web.xml file, either. 
This web application is 100% pure Java and you did not have to deal with configuring any plumbing or infrastructure.

The bean defined in the listenerAdapter() method is registered as a message listener in the container 
(defined in container()). It listens for messages on the spring-boot queue. 
Because the Receiver class is a POJO, it needs to be wrapped in the MessageListenerAdapter, 
where you specify that it invokes **receiveMessage()**.

```
JMS queues and AMQP queues have different semantics. For example, JMS sends queued messages to only one consumer. 
While AMQP queues do the same thing, AMQP producers do not send messages directly to queues. 
Instead, a message is sent to an exchange, which can go to a single queue or fan out to multiple queues, emulating the concept of JMS topics.
```

The message listener container and receiver beans are all you need to listen for messages. 
To send a message, you also need a Rabbit template.

The queue() method creates an AMQP queue. The exchange() method creates a topic exchange. The binding() method binds these two together, defining the behavior that occurs when RabbitTemplate publishes to an exchange.
```
Spring AMQP requires that the Queue, the TopicExchange, and the Binding be declared as top-level Spring beans in order to be set up properly.
```
In this case, we use a topic exchange, and the queue is bound with a routing key of foo.bar.#, which means that any messages sent with a routing key that begins with foo.bar. are routed to the queue.

### Send a Test Message
In this sample, test messages are sent by a CommandLineRunner, which also waits for the latch in the receiver and closes the application context. The following listing (from src/main/java/com.example.messagingrabbitmq/Runner.java) shows how it works:
```
package com.example.messagingrabbitmq;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

  private final RabbitTemplate rabbitTemplate;
  private final Receiver receiver;

  public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
    this.receiver = receiver;
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Sending message...");
    rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
    receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
  }

}
```
Notice that the template routes the message to the exchange with a routing key of foo.bar.baz, which matches the binding.

In tests, you can mock out the runner so that the receiver can be tested in isolation.

###Run the Application
The main() method starts that process by creating a Spring application context. 
This starts the message listener container, which starts listening for messages. 
There is a Runner bean, which is then automatically run. 
It retrieves the RabbitTemplate from the application context and sends a Hello from RabbitMQ! message on the spring-boot queue. Finally, it closes the Spring application context, and the application ends.

