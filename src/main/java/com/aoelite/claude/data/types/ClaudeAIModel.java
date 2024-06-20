package com.aoelite.claude.data.types;

import lombok.Getter;

@Getter
public enum ClaudeAIModel implements AIModel {

    HAIKU("claude-3-haiku-20240307"),
    SONNET("claude-3-sonnet-20240229"),
    OPUS("claude-3-opus-20240229"),
    SONNET_3_5("claude-3-5-sonnet-20240620");

    private final String id;

    ClaudeAIModel(String id) {
        this.id = id;
    }

    public static ClaudeAIModel fromId(String id) {
        for (ClaudeAIModel model : values()) {
            if (model.id.equals(id)) {
                return model;
            }
        }
        throw new IllegalArgumentException("Unknown model id: " + id);
    }

}
