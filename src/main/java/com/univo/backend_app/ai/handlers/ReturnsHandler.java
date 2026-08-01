package com.univo.backend_app.ai.handlers;


import com.univo.backend_app.ai.Intent;
import com.univo.backend_app.ai.prompts.ReturnsPromptBuilder;
import com.univo.backend_app.repositories.AIRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class ReturnsHandler implements AIHandler {


    private final AIRepository repository;
    private final ReturnsPromptBuilder promptBuilder;
    private final ChatClient chatClient;


    public ReturnsHandler(
            AIRepository repository,
            ReturnsPromptBuilder promptBuilder,
            ChatClient.Builder builder) {


        this.repository = repository;
        this.promptBuilder = promptBuilder;
        this.chatClient = builder.build();

    }


    @Override
    public Intent supports() {

        return Intent.RETURNS;

    }


    @Override
    public String handle(String question) {


        List<Map<String,Object>> returns =
                repository.getReturns();


        String prompt =
                promptBuilder.build(returns);


        return chatClient
                .prompt(prompt)
                .call()
                .content();

    }

}