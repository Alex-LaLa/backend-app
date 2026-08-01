package com.univo.backend_app.ai.prompts;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class LowStockPromptBuilder {

    public String build(List<Map<String, Object>> products) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
                Eres un consultor experto en administración de inventarios.
                
                Analiza los siguientes productos del inventario.
                
                """);

        for (Map<String, Object> product : products) {

            prompt.append("""
                    
                    Producto: %s
                    Categoría: %s
                    Stock actual: %s
                    Nivel de reorden: %s
                    Estado calculado por el sistema: %s
                    
                    """
                    .formatted(
                            product.get("nombre"),
                            product.get("categoria"),
                            product.get("unidades_disponibles"),
                            product.get("nivel_reorden"),
                            product.get("estado_stock")
                    ));

        }

        prompt.append("""
        IMPORTANTE:
        El campo "Estado oficial calculado por el backend" fue generado por el backend
        usando reglas matemáticas de inventario.

        No recalcules el estado.
        No modifiques la clasificación.
        No uses criterio humano para cambiar la categoría.
        Utiliza únicamente el estado proporcionado por el sistema.


        Estados posibles:

        1. CRÍTICO:
           Requiere reposición prioritaria.
           Son productos con riesgo de agotarse.

        2. ATENCIÓN:
           Requiere seguimiento y reposición preventiva.
           No representa una emergencia, pero debe planificarse una acción.

        3. NORMAL:
           No requiere acción inmediata.


        Formato obligatorio para analizar cada producto:

        Producto:
        Stock actual:
        Nivel de reorden:
        Estado oficial calculado por el backend:

        Análisis:
        (Explica brevemente la situación del producto)


        Ejemplo:

        Producto: Queso Oaxaca 400g
        Stock actual: 4
        Nivel de reorden: 5
        Estado oficial calculado por el backend: ATENCIÓN

        Análisis:
        El producto tiene un nivel de inventario cercano al punto de reorden,
        por lo que se recomienda planificar una reposición preventiva.


        Después del análisis indica:

        1. Productos que requieren reposición prioritaria:
           Incluye únicamente productos con estado CRÍTICO.

        2. Productos que requieren reposición preventiva:
           Incluye productos con estado ATENCIÓN que necesiten planificación de compra.

        3. Productos con mayor urgencia:
           Ordena los productos por cantidad de stock disponible ascendente.
           Los productos CRÍTICO tienen prioridad sobre ATENCIÓN.
           Si no existen productos CRÍTICO, muestra los productos ATENCIÓN
           con menor stock disponible.

        4. Recomendaciones prácticas para el dueño del negocio:

           Para productos ATENCIÓN:
           - Si el stock está muy cercano al nivel de reorden, recomienda planificar compra.
           - Si el stock está exactamente en el nivel de reorden, recomienda generar pedido preventivo.
           - Si el stock está por encima del 50% del nivel de reorden, recomienda únicamente monitoreo.


        Responde en español.
        """);

        return prompt.toString();

    }

}