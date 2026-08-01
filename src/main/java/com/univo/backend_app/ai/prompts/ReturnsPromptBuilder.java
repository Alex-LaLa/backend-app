package com.univo.backend_app.ai.prompts;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class ReturnsPromptBuilder {


    public String build(List<Map<String,Object>> returns){


        StringBuilder prompt = new StringBuilder();


        prompt.append("""
                
                Eres un analista experto en operaciones de pequeños negocios.

                Analiza las siguientes devoluciones registradas:

                """);


        for(Map<String,Object> item : returns){

            prompt.append("""

                    Estado de devolución: %s
                    Cantidad de devoluciones: %s
                    Productos devueltos: %s
                    Monto reembolsado: $%s

                    """
                    .formatted(
                            item.get("estado"),
                            item.get("cantidad_devoluciones"),
                            item.get("productos_devueltos"),
                            item.get("monto_reembolsado_total")
                    ));

        }


        prompt.append("""

                Genera un análisis del comportamiento de devoluciones.

                Incluye:

                - Total de devoluciones.
                - Estados de las devoluciones.
                - Impacto económico de los reembolsos.
                - Posibles recomendaciones para reducir devoluciones.

                No inventes información.
                Usa únicamente los datos proporcionados.

                Responde en español.

                """);


        return prompt.toString();

    }

}