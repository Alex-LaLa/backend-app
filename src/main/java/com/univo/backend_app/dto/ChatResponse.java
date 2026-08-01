package com.univo.backend_app.dto;

public class ChatResponse {

    private String respuesta;

    public ChatResponse() {
    }

    public ChatResponse(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}