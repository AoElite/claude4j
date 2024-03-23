package com.aoelite.claude.data.input;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TextMessage implements ClaudeMessage {

    private String content;

    @Override
    public JsonObject toJsonObject() {
        JsonObject object = new JsonObject();
        object.addProperty("type", "text");
        object.addProperty("text", content);
        return object;
    }
}
