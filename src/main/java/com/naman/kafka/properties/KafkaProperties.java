package com.naman.kafka.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka.properties")
@Data
public class KafkaProperties {
    String applicationId;
    String bootstrapServer;
    String keySerializer;
    String valueSerializer;
    String topics;
}
