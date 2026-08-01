package com.univo.backend_app.ai.prompts;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PendingPaymentsPromptBuilder {


    public String build(List<Map<String,Object>> payments){


        StringBuilder prompt = new StringBuilder();


        prompt.append("""
                
                Eres un analista financiero especializado en pequeños negocios.

                Analiza los siguientes pagos pendientes:

                """);


        for(Map<String,Object> payment : payments){

            prompt.append("""

                    Método de pago: %s
                    Cantidad de pagos pendientes: %s
                    Monto pendiente: $%s

                    """
                    .formatted(
                            payment.get("metodo_pago"),
                            payment.get("cantidad_pagos_pendientes"),
                            payment.get("monto_pendiente")
                    ));

        }


        prompt.append("""

                Genera un análisis financiero.

                Incluye:

                - Total de pagos pendientes.
                - Monto pendiente por recuperar.
                - Métodos de pago involucrados.
                - Recomendaciones para mejorar la cobranza.

                No inventes información.
                Usa únicamente los datos proporcionados.
                No asumas la causa del estado pendiente.
                Solo indica que existe un pago registrado como pendiente.
                
                Responde en español.

                """);


        return prompt.toString();

    }

}