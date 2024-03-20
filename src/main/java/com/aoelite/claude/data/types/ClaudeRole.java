package com.aoelite.claude.data.types;

import lombok.Getter;

@Getter
public enum ClaudeRole {

    USER("user"),
    ASSISTANT("assistant");

    private final String id;

    ClaudeRole(String id) {
        this.id = id;
    }

    public static ClaudeRole fromId(String id) {
        for (ClaudeRole role : values()) {
            if (role.id.equals(id)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role id: " + id);
    }

}
