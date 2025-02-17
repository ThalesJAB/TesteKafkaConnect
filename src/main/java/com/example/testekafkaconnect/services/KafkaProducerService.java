package com.example.testekafkaconnect.services;

import com.example.testekafkaconnect.model.User;
import org.slf4j.ILoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "meu-topico";  // Nome do tópico Kafka

    private final KafkaTemplate<String, User> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user) {
        kafkaTemplate.send(TOPIC, user);
        System.out.println("Mensagem enviada: " + user);
    }
}