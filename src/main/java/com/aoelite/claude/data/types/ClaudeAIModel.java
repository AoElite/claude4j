package com.aoelite.claude.data.types;

import lombok.Getter;

@Getter
public enum ClaudeAIModel implements AIModel {

    HAIKU_3_5("claude-3-5-haiku-latest"),
    SONNET_3_5("claude-3-5-sonnet-latest"),
    SONNET_3_7("claude-3-7-sonnet-latest"),
    OPUS("claude-3-opus-latest");

    private final String id;

    ClaudeAIModel(String id) {
        this.id = id;
    }

    public static AIModel fromId(String id) {
        return () -> id;
    }

}
