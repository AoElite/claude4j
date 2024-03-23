package com.aoelite.claude.example;

import com.aoelite.claude.ClaudeClient;
import com.aoelite.claude.api.ClaudeAPI;
import com.aoelite.claude.data.ClaudeRequest;
import com.aoelite.claude.data.ClaudeResponse;
import com.aoelite.claude.data.input.RoleInput;
import com.aoelite.claude.data.input.TextMessage;
import com.aoelite.claude.data.types.ClaudeModel;
import com.aoelite.claude.data.types.ClaudeRole;

public class ExampleA {

    public static void main(String[] args) {

        ClaudeAPI client = new ClaudeClient("YOUR_API_KEY");

        ClaudeResponse response = client.sendRequest(ClaudeRequest.builder()
                .model(ClaudeModel.HAIKU)
                .max_tokens(100)
                .temperature(0)
                .stop_sequence("###")
                .system("You are a AI math assistant")
                .input(RoleInput.builder()
                        .role(ClaudeRole.USER)
                        .message(new TextMessage("what's 2+2?"))
                        .build())
                .input(RoleInput.builder()
                        .role(ClaudeRole.ASSISTANT)
                        .message(new TextMessage("4"))
                        .build())
                .input(RoleInput.builder()
                        .role(ClaudeRole.USER)
                        .message(new TextMessage("what's 3+3?"))
                        .build())
                .build());

        if (response != null) { // response could timeout
            for (String string : response.getContent()) {
                System.out.println(string);
            }
        }

    }

}
