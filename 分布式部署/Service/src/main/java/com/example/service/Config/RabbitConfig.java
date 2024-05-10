package com.example.service.Config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
        @Bean("topic")
        public Exchange topic(){
            return ExchangeBuilder.topicExchange("topic_exchange").durable(true).build();
        }

        @Bean("lyy")
        public Queue MqQueue(){
            return QueueBuilder.durable("body_queue").build();
        }

        @Bean
        public Binding bindMq2(@Qualifier("topic" )Exchange exchange, @Qualifier("lyy")Queue queue) {
            return BindingBuilder.bind(queue).to(exchange).with("ly.#").noargs();
        }
}
