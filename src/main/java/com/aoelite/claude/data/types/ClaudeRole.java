package com.aoelite.claude.data.types;

import com.aoelite.claude.data.input.RoleInput;
import com.aoelite.claude.data.input.TextMessage;
import lombok.Getter;

@Getter
public enum ClaudeRole {

    USER("user"),
    ASSISTANT("assistant");

    private final String id;

    ClaudeRole(String id) {
        this.id = id;
    }

    public RoleInput text(String text) {
        return RoleInput.builder()
                .role(this)
                .message(new TextMessage(text))
                .build();
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
