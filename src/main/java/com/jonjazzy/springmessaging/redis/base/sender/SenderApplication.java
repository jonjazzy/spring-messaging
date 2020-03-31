package com.jonjazzy.springmessaging.redis.base.sender;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jonjazzy.springmessaging.redis.base.subscriber.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

//@SpringBootApplication
@EnableScheduling   //The @EnableScheduling annotation ensures that a background task executor is created. Without it, nothing gets scheduled.
public class SenderApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SenderApplication.class);
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /*
     *      To send a message, you also need a Redis template.
     *      Here, it is a bean configured as a StringRedisTemplate,
     *      an implementation of RedisTemplate that is focused on the common use of Redis,
     *      where both keys and values are String instances.
     */
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }


    public static void main(String[] args) throws InterruptedException {

//        ApplicationContext ctx =
                SpringApplication.run(SenderApplication.class, args);

        /*  The main() method then retrieves the StringRedisTemplate bean from the application context  */
//        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);

//        Receiver receiver = ctx.getBean(Receiver.class);

//        Sender.reportCurrentTime(template);

        /*  and uses it to send a Hello from Redis! message on the 'transaction' topic.  */
//        while (receiver.messageHandlingMethod() == 0) {

//            LOGGER.info("Sending transaction...");
//            template.convertAndSend("transaction", "Transaction --> " + dateFormat.format(new Date()));
//            LOGGER.info("Message sent! Seeping thread \uD83D\uDE01");
//            Thread.sleep(500L);
//        }

//        System.exit(0);
    }

//    @Scheduled(fixedRate = 5000)
//    public static void reportCurrentTime(StringRedisTemplate template) throws InterruptedException {
//        LOGGER.info("Sending transaction...");
//        template.convertAndSend("transaction", "Transaction --> " + dateFormat.format(new Date()));
//        LOGGER.info("Message sent! Seeping thread \uD83D\uDE01");
//        Thread.sleep(500L);
//    }

}
