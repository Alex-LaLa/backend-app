package com.univo.backend_app.ai.prompts;

import org.springframework.stereotype.Component;

@Component
public class IntentPromptBuilder {

    public String build(String question){

        return """
                Clasifica la siguiente pregunta del usuario en una sola intención.

                Intenciones disponibles:

                LOW_STOCK
                TOP_PRODUCTS
                TOP_CLIENTS
                SALES_BY_CATEGORY
                PAYMENT_METHODS
                PENDING_PAYMENTS
                RETURNS
                SALES_TODAY
                BUSINESS_SUMMARY

                Responde únicamente con el nombre de la intención.

                Pregunta:
                %s
                """.formatted(question);

    }

}