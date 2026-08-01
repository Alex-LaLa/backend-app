package com.univo.backend_app.ai.handlers;

import com.univo.backend_app.ai.Intent;
import com.univo.backend_app.ai.StockClassifier;
import com.univo.backend_app.ai.prompts.LowStockPromptBuilder;
import com.univo.backend_app.repositories.AIRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class LowStockHandler implements AIHandler {

    private final AIRepository repository;
    private final LowStockPromptBuilder promptBuilder;
    private final ChatClient.Builder chatClientBuilder;
    private final StockClassifier stockClassifier;

    public LowStockHandler(
            AIRepository repository,
            LowStockPromptBuilder promptBuilder,
            ChatClient.Builder chatClientBuilder,
            StockClassifier stockClassifier
    ) {
        this.repository = repository;
        this.promptBuilder = promptBuilder;
        this.chatClientBuilder = chatClientBuilder;
        this.stockClassifier = stockClassifier;
    }

    @Override
    public Intent supports() {

        return Intent.LOW_STOCK;

    }

    @Override
    public String handle(String question) {

        List<Map<String,Object>> products = repository.getLowStockProducts();

        for (Map<String,Object> product : products) {

            int stock = Integer.parseInt(
                    product.get("unidades_disponibles").toString()
            );

            int reorden = Integer.parseInt(
                    product.get("nivel_reorden").toString()
            );

            String estado = stockClassifier.clasificarStock(stock, reorden);

            product.put("estado_stock", estado);
        }

        String prompt = promptBuilder.build(products);

        return chatClientBuilder
                .build()
                .prompt(prompt)
                .call()
                .content();

    }


}