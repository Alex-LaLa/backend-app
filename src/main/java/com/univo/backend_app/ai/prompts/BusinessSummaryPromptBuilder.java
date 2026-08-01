package com.univo.backend_app.ai.prompts;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BusinessSummaryPromptBuilder {


    public String build(Map<String,Object> summary){


        return """

                Eres un analista experto en pequeños negocios.

                Analiza los siguientes datos reales del negocio:

                %s


                Genera un resumen ejecutivo incluyendo:

                - Situación general del negocio.
                - Estado del inventario.
                - Ventas y clientes.
                - Riesgos detectados.
                - Recomendaciones prácticas.


                Reglas:

                - No inventes información.
                - Usa únicamente los datos proporcionados.
                - No agregues fechas ni datos que no existan.
                - Si detectas problemas, explica el motivo.
                - Responde en español.

                """.formatted(summary);

    }

}