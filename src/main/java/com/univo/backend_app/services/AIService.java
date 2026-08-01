package com.univo.backend_app.services;

import com.univo.backend_app.ai.Intent;
import com.univo.backend_app.ai.IntentDetector;
import com.univo.backend_app.ai.handlers.AIHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIService {

    private final IntentDetector detector;
    private final List<AIHandler> handlers;

    public AIService(IntentDetector detector,
                     List<AIHandler> handlers) {

        this.detector = detector;
        this.handlers = handlers;

    }

    public String chat(String question){

        Intent intent =
                detector.detect(question);

        for(AIHandler handler : handlers){

            if(handler.supports() == intent){

                return handler.handle(question);

            }

        }

        return """
                Lo siento, todavía no puedo responder esa consulta.
                """;

    }

}