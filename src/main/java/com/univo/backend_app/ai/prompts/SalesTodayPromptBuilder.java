package com.univo.backend_app.ai.prompts;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class SalesTodayPromptBuilder {


    public String build(List<Map<String,Object>> sales){


        StringBuilder prompt = new StringBuilder();


        prompt.append("""
                
                Eres un analista experto en ventas para pequeños negocios.

                Analiza las ventas realizadas el día de hoy.

                Datos:

                """);


        for(Map<String,Object> sale : sales){


            prompt.append("""
                    
                    Ventas realizadas: %s
                    Ingresos totales: $%s
                    
                    """
                    .formatted(
                            sale.get("ventas_realizadas"),
                            sale.get("ingresos_totales")
                    ));

        }


        prompt.append("""
                
                Realiza un análisis comercial.

                Incluye:

                - Resumen de ventas del día.
                - Interpretación de los ingresos obtenidos.
                - Recomendaciones prácticas para el negocio.

                No inventes información.
                Usa únicamente los datos proporcionados.

                Responde en español.

                """);


        return prompt.toString();

    }

}