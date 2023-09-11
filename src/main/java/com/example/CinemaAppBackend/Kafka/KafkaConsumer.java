package com.example.CinemaAppBackend.Kafka;

import com.example.CinemaAppBackend.Entity.Booking;
import com.example.CinemaAppBackend.Entity.Seat;
import com.example.CinemaAppBackend.Repo.SeatRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;





@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    //------------
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaConsumer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    private SeatRepo seatRepo;

    //------------------


    @KafkaListener(topics = "Seat_Booking_Test", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Booking booking) throws JsonProcessingException {


        LOGGER.info(String.format("Message received -> %s", booking.toString()));

        //---------




        long seatId = booking.getSeatId();
        long numberOfSeats = booking.getNumberOfSeats();
//        Seat seat = new Seat();
//        seat.setSeatId(seatId);
        Seat seat = seatRepo.findById(seatId).orElse(null);

        if(numberOfSeats > seat.getAvailableSeat()){

            String newMessage = "Cannot make booking, Requested capacity is greater than the available capacity right now";
            // Send the new message to another Kafka topic
            kafkaTemplate.send("Validation_Test", newMessage);

        }else{
            seat.setAvailableSeat(seat.getAvailableSeat() - numberOfSeats);
            seatRepo.save(seat);

            String newMessage = "Booking is valid";
            // Send the new message to another Kafka topic
            kafkaTemplate.send("Validation_Test", newMessage);

        }
        //---------
    }
}
