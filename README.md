A very simple java library to interact with Claude's API

Example usage:
```java
        ClaudeAPI client = new ClaudeClient("YOUR_API_KEY");

        ClaudeResponse response = client.sendRequest(ClaudeRequest.builder()
                .model(ClaudeAIModel.HAIKU)
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
```

You can create a API key [here](https://console.anthropic.com/settings/keys) once you have created a Anthropic account.

TODO:
- [X] Image/File support
- [ ] Stream support
- [ ] Embeddings
- [ ] Legacy models
