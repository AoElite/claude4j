package com.aoelite.claude.example;

import com.aoelite.claude.ClaudeClient;
import com.aoelite.claude.api.ClaudeAPI;
import com.aoelite.claude.data.ClaudeRequest;
import com.aoelite.claude.data.ClaudeResponse;
import com.aoelite.claude.data.types.ClaudeAIModel;
import com.aoelite.claude.data.types.ClaudeRole;

public class ExampleA {

    public static void main(String[] args) {

        ClaudeAPI client = new ClaudeClient(System.getenv("CLAUDE_API_KEY"));

        ClaudeResponse response = client.sendRequest(ClaudeRequest.builder()
                .model(ClaudeAIModel.HAIKU_3_5)
                .max_tokens(100)
                .temperature(0)
                .stop_sequence("###")
                .system("You are a AI math assistant")
                .input(ClaudeRole.USER.text("what's 2+2?"))
                .input(ClaudeRole.ASSISTANT.text("4"))
                .input(ClaudeRole.USER.text("what's 3+3?"))
                .build());

        if (response != null) { // response could timeout
            for (String string : response.getContent()) {
                System.out.println(string);
            }
        }

    }

}
