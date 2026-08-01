package com.univo.backend_app.ai.handlers;


import com.univo.backend_app.ai.Intent;
import com.univo.backend_app.ai.prompts.SalesTodayPromptBuilder;
import com.univo.backend_app.repositories.AIRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class SalesTodayHandler implements AIHandler {


    private final AIRepository repository;
    private final SalesTodayPromptBuilder promptBuilder;
    private final ChatClient chatClient;


    public SalesTodayHandler(
            AIRepository repository,
            SalesTodayPromptBuilder promptBuilder,
            ChatClient.Builder builder) {


        this.repository = repository;
        this.promptBuilder = promptBuilder;
        this.chatClient = builder.build();

    }


    @Override
    public Intent supports() {

        return Intent.SALES_TODAY;

    }


    @Override
    public String handle(String question) {


        List<Map<String,Object>> sales =
                repository.getSalesToday();


        String prompt =
                promptBuilder.build(sales);


        return chatClient
                .prompt(prompt)
                .call()
                .content();

    }

}