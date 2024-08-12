package com.aoelite.claude.example;

import com.aoelite.claude.ClaudeClient;
import com.aoelite.claude.api.ClaudeAPI;
import com.aoelite.claude.data.ClaudeRequest;
import com.aoelite.claude.data.input.RoleInput;
import com.aoelite.claude.data.input.TextMessage;
import com.aoelite.claude.data.stream.ClaudeStreamResponse;
import com.aoelite.claude.data.types.ClaudeAIModel;
import com.aoelite.claude.data.types.ClaudeRole;

import java.util.concurrent.CompletableFuture;

public class ExampleB {

    public static void main(String[] args) {

        ClaudeAPI client = new ClaudeClient("YOUR_API_KEY");

        CompletableFuture<ClaudeStreamResponse> response = client.sendStreamRequestAsync(ClaudeRequest.builder()
                .model(ClaudeAIModel.HAIKU)
                .max_tokens(50)
                .temperature(0)
                .stop_sequence("###")
                .input(RoleInput.builder()
                        .role(ClaudeRole.USER)
                        .message(new TextMessage("how far is the sun from the earth?"))
                        .build())
                .build(), (stream, message) -> System.out.println(message));
        //
        response.thenAccept(stream -> {
            if (stream != null) {
                System.out.println("Stream ended");
            }
        });

    }

}
