package com.univo.backend_app.controllers;

import com.univo.backend_app.dto.ChatRequest;
import com.univo.backend_app.dto.ChatResponse;
import com.univo.backend_app.services.AIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {

        String response =
                aiService.chat(request.getPregunta());

        return new ChatResponse(response);

    }

}   