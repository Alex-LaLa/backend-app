package com.univo.backend_app.ai.prompts;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TopProductsPromptBuilder {


    public String build(List<Map<String,Object>> products){


        StringBuilder prompt = new StringBuilder();


        prompt.append("""
                
                Eres un analista experto en ventas para pequeños negocios.

                Analiza los siguientes productos con mayor rendimiento de ventas:

                """);


        for(Map<String,Object> product : products){

            prompt.append("""

                    Producto: %s
                    Categoría: %s
                    Unidades vendidas: %s
                    Ingresos generados: $%s

                    """
                    .formatted(
                            product.get("nombre"),
                            product.get("categoria"),
                            product.get("unidades_vendidas"),
                            product.get("ingresos_generados")
                    ));

        }


        prompt.append("""

                Genera un análisis comercial.

                Incluye:

                - Productos con mejor desempeño.
                - Cuáles generan más ingresos.
                - Posibles productos estrella.
                - Recomendaciones para aumentar ventas.

                No inventes información.
                Usa únicamente los datos proporcionados.

                Responde en español.

                """);


        return prompt.toString();

    }

}