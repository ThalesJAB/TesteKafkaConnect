package com.example.testekafkaconnect.controllers;

import com.example.testekafkaconnect.model.User;
import com.example.testekafkaconnect.services.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class KafkaController {

    private final KafkaProducerService kafkaProducerService;

    public KafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        kafkaProducerService.sendMessage(user);
        return ResponseEntity.ok("User added successfully");
    }
}
