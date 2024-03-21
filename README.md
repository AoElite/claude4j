A very simple java library to interact with Claude's API

Example usage:
```java
        ClaudeAPI client = new ClaudeClient("YOUR_API_KEY");

        ClaudeResponse response = client.sendRequest(ClaudeRequest.builder()
                .model(ClaudeModel.HAIKU)
                .max_tokens(100)
                .message(new ClaudeMessage(ClaudeRole.USER, "what's 2+2?"))
                .build());

        // response could be null if it timed out
        if (response != null) {
            for (String string : response.getContent()) {
                System.out.println(string);
            }
        }
```

TODO:
- [ ] Image/File support
- [ ] Stream support
- [ ] Embeddings
- [ ] Legacy models
