package com.naman.kafka.controller;

import com.naman.kafka.properties.KafkaProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProperties kafkaProperties;
    private final KafkaProducer kafkaProducer;
    private final Logger log  = LoggerFactory.getLogger(KafkaController.class);

    public KafkaController(final KafkaProperties kafkaProperties, final KafkaProducer kafkaProducer) {
        this.kafkaProperties = kafkaProperties;
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/sendmsg")
    public ResponseEntity<?> sendSimpleMsg(){
        log.info("Request to send message received");
        for (int i = 0; i<1000000 ; i++){
            ProducerRecord<String, String> record = new ProducerRecord<>(kafkaProperties.getTopics(), "Simple Message - "+i);
            kafkaProducer.send(record);
        }
        log.info("Message Sent, closing Producer");
        kafkaProducer.close();
        return new ResponseEntity<>("Message Sent", HttpStatus.OK);
    }
}
