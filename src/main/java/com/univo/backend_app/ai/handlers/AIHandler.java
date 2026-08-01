package com.univo.backend_app.ai.handlers;

import com.univo.backend_app.ai.Intent;

public interface AIHandler {

    Intent supports();

    String handle(String question);

}