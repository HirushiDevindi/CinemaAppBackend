package com.example.CinemaAppBackend.Kafka;

import com.example.CinemaAppBackend.Entity.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private KafkaTemplate<String, Booking> kafkaTemplate;

    private static final Logger LOGGER =   LoggerFactory.getLogger(KafkaProducer.class);

    public KafkaProducer(KafkaTemplate<String, Booking> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Booking booking){
        LOGGER.info(String.format("message sent %s", booking.toString()));
        Message<Booking> msg = MessageBuilder
                .withPayload(booking)
                        .setHeader(KafkaHeaders.TOPIC, "Seat_Booking_Test")
                                .build();

        kafkaTemplate.send(msg);
    }
}
