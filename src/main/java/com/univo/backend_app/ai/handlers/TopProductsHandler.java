package com.univo.backend_app.ai.handlers;


import com.univo.backend_app.ai.Intent;
import com.univo.backend_app.ai.prompts.TopProductsPromptBuilder;
import com.univo.backend_app.repositories.AIRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class TopProductsHandler implements AIHandler {


    private final AIRepository repository;
    private final TopProductsPromptBuilder promptBuilder;
    private final ChatClient chatClient;


    public TopProductsHandler(
            AIRepository repository,
            TopProductsPromptBuilder promptBuilder,
            ChatClient.Builder builder) {


        this.repository = repository;
        this.promptBuilder = promptBuilder;
        this.chatClient = builder.build();

    }


    @Override
    public Intent supports() {

        return Intent.TOP_PRODUCTS;

    }


    @Override
    public String handle(String question) {


        List<Map<String,Object>> products =
                repository.getTopProducts();


        String prompt =
                promptBuilder.build(products);


        return chatClient
                .prompt(prompt)
                .call()
                .content();

    }

}