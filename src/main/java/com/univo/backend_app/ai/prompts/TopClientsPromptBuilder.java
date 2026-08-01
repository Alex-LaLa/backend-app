package com.univo.backend_app.ai.prompts;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TopClientsPromptBuilder {


    public String build(List<Map<String,Object>> clients){


        StringBuilder prompt = new StringBuilder();


        prompt.append("""
                
                Eres un analista experto en comportamiento de clientes.

                Analiza los siguientes clientes con mayor participación en ventas:

                """);


        for(Map<String,Object> client : clients){

            prompt.append("""

                    Cliente: %s
                    Correo: %s
                    Ciudad: %s
                    Total de compras: %s
                    Total gastado: $%s

                    """
                    .formatted(
                            client.get("nombre_completo"),
                            client.get("email"),
                            client.get("ciudad"),
                            client.get("total_ordenes"),
                            client.get("total_gastado")
                    ));

        }


        prompt.append("""

                Genera un análisis de clientes.

                Incluye:

                - Clientes más importantes.
                - Clientes frecuentes.
                - Clientes con mayor valor económico.
                - Recomendaciones de fidelización.

                No inventes información.
                Usa únicamente los datos proporcionados.
                No sugieras funcionalidades o servicios que no estén relacionados con el negocio actual.
                
                Responde en español.

                """);


        return prompt.toString();

    }

}