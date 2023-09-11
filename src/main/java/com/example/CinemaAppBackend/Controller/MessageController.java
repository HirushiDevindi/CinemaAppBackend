package com.example.CinemaAppBackend.Controller;

import com.example.CinemaAppBackend.Entity.Booking;
import com.example.CinemaAppBackend.Kafka.KafkaProducer;
//import com.example.CinemaAppBackend.Kafka.ValidateProducer;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/kafka")
@EnableAutoConfiguration
public class MessageController {

    private KafkaProducer kafkaProducer;

    @Autowired
    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    // http:localhost:8000/api/v1/kafka/booking?message=test1
    @GetMapping("/booking")
    public ResponseEntity<String> publish(@RequestBody Booking booking){
        kafkaProducer.sendMessage(booking);
        return ResponseEntity.ok("Message sent to the topic - Seat_Booking");
    }

//    private ValidateProducer validateProducer;
//    @Autowired
//    public MessageController(ValidateProducer validateProducer) {
//        this.validateProducer = validateProducer;
//    }
//
//    // http:localhost:8000/api/v1/kafka/booking/validate?message=Booking confirmed. Do Payments
//    @GetMapping("/booking/validate")
//    public ResponseEntity<String> bookingValidate(@RequestParam("message") String msg){
//        validateProducer.sendMessage(msg);
//        return ResponseEntity.ok("Message sent to the topic - Validation");
//    }
}
