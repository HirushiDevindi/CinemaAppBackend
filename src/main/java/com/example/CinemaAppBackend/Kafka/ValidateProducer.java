//package com.example.CinemaAppBackend.Kafka;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ValidateProducer {
//    private KafkaTemplate<StackTraceElement,String > kafkaTemplate;
//
//    private static final Logger LOGGER =   LoggerFactory.getLogger(ValidateProducer.class);
//    public ValidateProducer(KafkaTemplate<StackTraceElement, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendMessage(String msg){
//        LOGGER.info(String.format("Booking validation message sent %s", msg));
//        kafkaTemplate.send("Validation", msg);
//    }
//}
