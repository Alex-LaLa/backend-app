package com.univo.backend_app.ai.handlers;


import com.univo.backend_app.ai.Intent;
import com.univo.backend_app.ai.prompts.SalesByCategoryPromptBuilder;
import com.univo.backend_app.repositories.AIRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class SalesByCategoryHandler implements AIHandler {


    private final AIRepository repository;
    private final SalesByCategoryPromptBuilder promptBuilder;
    private final ChatClient chatClient;


    public SalesByCategoryHandler(
            AIRepository repository,
            SalesByCategoryPromptBuilder promptBuilder,
            ChatClient.Builder builder) {


        this.repository = repository;
        this.promptBuilder = promptBuilder;
        this.chatClient = builder.build();

    }


    @Override
    public Intent supports() {

        return Intent.SALES_BY_CATEGORY;

    }


    @Override
    public String handle(String question) {


        List<Map<String,Object>> categories =
                repository.getSalesByCategory();


        String prompt =
                promptBuilder.build(categories);


        return chatClient
                .prompt(prompt)
                .call()
                .content();

    }

}