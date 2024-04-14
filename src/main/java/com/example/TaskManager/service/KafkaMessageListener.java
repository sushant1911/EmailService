package com.example.TaskManager.service;


import com.example.TaskManager.dto.TaskResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper; // Jackson ObjectMapper for JSON deserialization

    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "task_created", groupId = "task_created_group")
    public void consumeEvents(String jsonTask) { // Receive JSON string
        try {
            TaskResponseDTO taskResponseDTO = objectMapper.readValue(jsonTask, TaskResponseDTO.class); // Deserialize JSON string to TaskResponseDTO
      // Convert TaskResponseDTO to TaskDto if needed
            log.info("Task  consumed: {}", taskResponseDTO.getTitle());
            emailService.sendEmail(taskResponseDTO);
        } catch (JsonProcessingException ex) {
            log.error("Error deserializing JSON to TaskResponseDTO: {}", ex.getMessage());
        }
    }

    // Method to convert TaskResponseDTO to TaskDto
    private TaskResponseDTO convertToTaskDto(TaskResponseDTO taskResponseDTO) {
        // Implement conversion logic here
        return null;
    }
}
