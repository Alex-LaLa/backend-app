package com.univo.backend_app.ai;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PromptBuilder {

    public String buildLowStockPrompt(List<Map<String, Object>> products) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
                Eres un consultor experto en inventarios.

                Analiza los siguientes productos con inventario bajo.

                """);

        for (Map<String, Object> product : products) {

            prompt.append("""
                    
                    Producto: %s
                    Categoría: %s
                    Stock actual: %s
                    Nivel de reorden: %s
                    """
                    .formatted(
                            product.get("nombre"),
                            product.get("categoria"),
                            product.get("unidades_disponibles"),
                            product.get("nivel_reorden")
                    ));
        }

        prompt.append("""

                Explica cuáles requieren atención inmediata.

                No inventes información.

                Al final da una recomendación para el dueño del negocio.
                """);

        return prompt.toString();

    }

}