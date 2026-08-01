package com.univo.backend_app.ai.prompts;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SalesByCategoryPromptBuilder {


    public String build(List<Map<String,Object>> categories){


        StringBuilder prompt = new StringBuilder();


        prompt.append("""
                
                Eres un analista experto en ventas para pequeños negocios.

                Analiza el desempeño de ventas por categoría.

                Datos:

                """);


        for(Map<String,Object> category : categories){

            prompt.append("""

                    Categoría: %s
                    Unidades vendidas: %s
                    Ingresos generados: $%s

                    """
                    .formatted(
                            category.get("categoria"),
                            category.get("unidades_vendidas"),
                            category.get("ingresos_generados")
                    ));

        }


        prompt.append("""

                Realiza un análisis comercial.

                Incluye:

                - Categorías con mayor rendimiento.
                - Categorías que generan más ingresos.
                - Oportunidades de mejora.
                - Recomendaciones para el negocio.

                No inventes información.
                Usa únicamente los datos proporcionados.

                Responde en español.

                """);


        return prompt.toString();

    }

}