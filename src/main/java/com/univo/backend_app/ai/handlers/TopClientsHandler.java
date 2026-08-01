package com.univo.backend_app.ai.handlers;


import com.univo.backend_app.ai.Intent;
import com.univo.backend_app.ai.prompts.TopClientsPromptBuilder;
import com.univo.backend_app.repositories.AIRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class TopClientsHandler implements AIHandler {


    private final AIRepository repository;
    private final TopClientsPromptBuilder promptBuilder;
    private final   ChatClient chatClient;


    public TopClientsHandler(
            AIRepository repository,
            TopClientsPromptBuilder promptBuilder,
            ChatClient.Builder builder) {


        this.repository = repository;
        this.promptBuilder = promptBuilder;
        this.chatClient = builder.build();

    }


    @Override
    public Intent supports() {

        return Intent.TOP_CLIENTS;

    }


    @Override
    public String handle(String question) {


        List<Map<String,Object>> clients =
                repository.getTopClients();


        String prompt =
                promptBuilder.build(clients);


        return chatClient
                .prompt(prompt)
                .call()
                .content();

    }

}