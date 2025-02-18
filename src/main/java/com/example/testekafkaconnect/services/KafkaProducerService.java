package com.example.testekafkaconnect.services;

import com.example.testekafkaconnect.model.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KafkaProducerService {


    private static final String TOPIC = "meu-topico";  // Nome do tópico Kafka

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user) {
        // Criando estrutura esperada pelo Kafka Connect
        Map<String, Object> message = Map.of(
                "schema", Map.of(
                        "type", "struct",
                        "fields", List.of(
                                Map.of("field", "name", "type", "string"),
                                Map.of("field", "email", "type", "string")
                        )
                ),
                "payload", Map.of(
                        "name", user.getName(),
                        "email", user.getEmail()
                )
        );

        kafkaTemplate.send(TOPIC, null, message);
        System.out.println("Mensagem enviada: " + message);
    }
}