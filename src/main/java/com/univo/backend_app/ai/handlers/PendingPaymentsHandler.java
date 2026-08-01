package com.univo.backend_app.ai.handlers;


import com.univo.backend_app.ai.Intent;
import com.univo.backend_app.ai.prompts.PendingPaymentsPromptBuilder;
import com.univo.backend_app.repositories.AIRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class PendingPaymentsHandler implements AIHandler {


    private final AIRepository repository;
    private final PendingPaymentsPromptBuilder promptBuilder;
    private final ChatClient chatClient;


    public PendingPaymentsHandler(
            AIRepository repository,
            PendingPaymentsPromptBuilder promptBuilder,
            ChatClient.Builder builder) {


        this.repository = repository;
        this.promptBuilder = promptBuilder;
        this.chatClient = builder.build();

    }


    @Override
    public Intent supports() {

        return Intent.PENDING_PAYMENTS;

    }


    @Override
    public String handle(String question) {


        List<Map<String,Object>> payments =
                repository.getPendingPayments();


        String prompt =
                promptBuilder.build(payments);


        return chatClient
                .prompt(prompt)
                .call()
                .content();

    }

}