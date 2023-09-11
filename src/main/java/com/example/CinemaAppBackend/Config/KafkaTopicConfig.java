package com.example.CinemaAppBackend.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic BookingTopic(){
        return TopicBuilder.name("Seat_Booking_Test")
                .build();
    }
    @Bean
    public NewTopic ValidationTopic(){
        return TopicBuilder.name("Validation_Test")
                .build();
    }
}
