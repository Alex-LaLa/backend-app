package com.univo.backend_app.ai.handlers;
import com.univo.backend_app.ai.prompts.BusinessSummaryPromptBuilder;
import com.univo.backend_app.ai.Intent;
import com.univo.backend_app.repositories.AIRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BusinessSummaryHandler implements AIHandler {

    private final BusinessSummaryPromptBuilder promptBuilder;
    private final AIRepository repository;
    private final ChatClient chatClient;


    public BusinessSummaryHandler(
            BusinessSummaryPromptBuilder promptBuilder, AIRepository repository,
            ChatClient.Builder builder) {

        this.repository = repository;
        this.promptBuilder = promptBuilder;
        this.chatClient = builder.build();

    }


    @Override
    public Intent supports() {

        return Intent.BUSINESS_SUMMARY;

    }


    @Override
    public String handle(String question) {


        Map<String,Object> summary =
                repository.getBusinessSummary();


        String prompt =
                promptBuilder.build(summary);


        return chatClient
                .prompt(prompt)
                .call()
                .content();

    }

}