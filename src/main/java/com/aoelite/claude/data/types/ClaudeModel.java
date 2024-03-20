package com.aoelite.claude.data.types;

import lombok.Getter;

@Getter
public enum ClaudeModel {

    HAIKU("claude-3-haiku-20240307"),
    SONNET("claude-3-sonnet-20240229"),
    OPUS("claude-3-opus-20240229");

    private final String id;

    ClaudeModel(String id) {
        this.id = id;
    }

    public static ClaudeModel fromId(String id) {
        for (ClaudeModel model : values()) {
            if (model.id.equals(id)) {
                return model;
            }
        }
        throw new IllegalArgumentException("Unknown model id: " + id);
    }

}
