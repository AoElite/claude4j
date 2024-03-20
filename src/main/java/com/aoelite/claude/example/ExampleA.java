package com.aoelite.claude.example;

import com.aoelite.claude.ClaudeClient;
import com.aoelite.claude.api.ClaudeAPI;
import com.aoelite.claude.data.ClaudeMessage;
import com.aoelite.claude.data.ClaudeRequest;
import com.aoelite.claude.data.ClaudeResponse;
import com.aoelite.claude.data.types.ClaudeModel;
import com.aoelite.claude.data.types.ClaudeRole;

public class ExampleA {

    public static void main(String[] args) {

        ClaudeAPI client = new ClaudeClient("YOUR_API_KEY");

        ClaudeResponse response = client.sendRequest(ClaudeRequest.builder()
                .model(ClaudeModel.HAIKU)
                .max_tokens(100)
                .temperature(1)
                .stop_sequence("###")
                .system("You are a AI math assistant")
                .message(new ClaudeMessage(ClaudeRole.USER, "what's 2+2?"))
                .build());

        // response could be null if it timed out
        if (response != null) {
            for (String string : response.getContent()) {
                System.out.println(string);
            }
        }

    }

}
