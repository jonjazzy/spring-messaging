package com.jonjazzy.springmessaging.rabbitmq.base.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling   //The @EnableScheduling annotation ensures that a background task executor is created. Without it, nothing gets scheduled.
public class ProducerRabbitmqApplication
{

    private static Logger LOGGER = LoggerFactory.getLogger(ProducerRabbitmqApplication.class);
    static final String topicExchangeName = "spring-boot-exchange";
    static final String queueName = "spring-boot";

    public static void main(String[] args) {
        SpringApplication.run(ProducerRabbitmqApplication.class, args);

        LOGGER.info("spring-schedulling-tasks");
        LOGGER.info("---------------------------------------");
    }

    @Bean
    /*  The queue() method creates an AMQP queue    */
    Queue queue() {
        return new Queue(queueName, false);     //Declare the queue
    }

    @Bean
    /*  The exchange() method creates a topic exchange  */
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);    //Declare the exchange the exchange
    }

    @Bean
    /*
        The binding() method binds these two (queue and exchage) together,
        defining the behavior that occurs when RabbitTemplate (i.e. Producer) publishes to an exchange
    */
    Binding binding(Queue queue, TopicExchange exchange)
    {
        //Declare the binding between queue and exchange
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");

        /*
         *   In this case, we use a topic exchange, and the queue is bound with a routing key of foo.bar.#,
         *   which means that any messages sent with a routing key that begins with foo.bar. are routed to the queue.
         */
    }
}