package com.univo.backend_app.ai.handlers;

import com.univo.backend_app.ai.Intent;
import org.springframework.stereotype.Component;

@Component
public class UnknownHandler implements AIHandler {

    @Override
    public Intent supports() {
        return Intent.UNKNOWN;
    }

    @Override
    public String handle(String question) {

        return """
        No encontré información relacionada con esa consulta.

        Puedo ayudarte con análisis del sistema de inventario,
        ventas, productos y clientes.
        """;
    }
}