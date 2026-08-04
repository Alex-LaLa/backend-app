package com.univo.backend_app.ai.handlers;

import com.univo.backend_app.ai.Intent;
import org.springframework.stereotype.Component;

@Component
public class GreetingHandler implements AIHandler {

    @Override
    public Intent supports() {
        return Intent.GREETING;
    }

    @Override
    public String handle(String question) {

        return """
        ¡Hola! 👋

        Soy el asistente inteligente del sistema de inventario.

        Puedo ayudarte con:
        
        📦 Inventario
        📈 Productos más vendidos
        👥 Mejores clientes
        💰 Ventas
        📊 Resumen del negocio
        """;
    }
}