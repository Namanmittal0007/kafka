package com.naman.kafka.config;

import com.naman.kafka.properties.KafkaProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;
    private final Logger log = LoggerFactory.getLogger(KafkaConfig.class);

    public KafkaConfig(final KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public KafkaProducer getProducer(){
        Properties props  = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProperties.getApplicationId());
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServer());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProperties.getKeySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProperties.getValueSerializer());
        log.info("Creating Kafka producer, producer properties : {}", props);
        return new KafkaProducer<>(props);
    }
}
