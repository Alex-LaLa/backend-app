package com.univo.backend_app.ai;

import com.univo.backend_app.ai.prompts.IntentPromptBuilder;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class IntentClassifierAI {

    private final ChatClient chatClient;
    private final IntentPromptBuilder promptBuilder;

    public IntentClassifierAI(
            ChatClient.Builder builder,
            IntentPromptBuilder promptBuilder
    ) {

        this.chatClient = builder.build();
        this.promptBuilder = promptBuilder;

    }

    public Intent classify(String question) {

        String response = chatClient
                .prompt(promptBuilder.build(question))
                .call()
                .content();


        if(response == null){
            return Intent.UNKNOWN;
        }


        response = response
                .trim()
                .toUpperCase()
                .replace(".", "");


        System.out.println("Intent detectado por IA: " + response);


        for(Intent intent : Intent.values()){

            if(response.contains(intent.name())){

                return intent;

            }

        }


        System.out.println("No fue posible interpretar el intent: " + response);

        return Intent.UNKNOWN;

    }

}