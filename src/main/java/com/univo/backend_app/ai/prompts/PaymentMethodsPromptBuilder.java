package com.univo.backend_app.ai.prompts;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PaymentMethodsPromptBuilder {


    public String build(List<Map<String,Object>> payments){


        StringBuilder prompt = new StringBuilder();


        prompt.append("""
                
                Eres un analista financiero especializado en pequeños negocios.

                Analiza los siguientes datos de métodos de pago:

                """);


        for(Map<String,Object> payment : payments){

            prompt.append("""

                    Método de pago: %s
                    Cantidad de pagos: %s
                    Monto total: $%s

                    """
                    .formatted(
                            payment.get("metodo_pago"),
                            payment.get("cantidad_pagos"),
                            payment.get("monto_total")
                    ));

        }


        prompt.append("""

                Genera un análisis financiero.

                Incluye:

                - Método de pago más utilizado.
                - Método que genera más ingresos.
                - Comparación entre métodos.
                - Recomendaciones para mejorar la gestión de pagos.

                No inventes información.
                Usa únicamente los datos proporcionados.

                Responde en español.

                """);


        return prompt.toString();

    }

}