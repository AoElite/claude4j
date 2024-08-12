package com.aoelite.claude.data.stream;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;

public class ClaudeStreamReader {

    private final ClaudeStreamHandler handler;
    @Getter private final ClaudeStreamResponse stream;

    public ClaudeStreamReader(ClaudeStreamHandler handler) {
        this.handler = handler;
        this.stream = new ClaudeStreamResponse();
    }

    public void process(String message) {
        if (message.startsWith("event:") || message.isBlank()) return;
        String data = message.substring(6);
        JsonObject object = JsonParser.parseString(data).getAsJsonObject();
        final String type = object.get("type").getAsString();
        if (type.equals("content_block_delta")) {
            JsonObject delta = object.getAsJsonObject("delta");
            String text = delta.get("text").getAsString();
            handler.onMessage(stream, text);
        }
    }

}
