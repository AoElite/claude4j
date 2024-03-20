package com.aoelite.claude.data.types;

import lombok.Getter;

@Getter
public enum ClaudeStopReason {

    END_TURN("end_turn"),
    MAX_TOKENS("max_tokens"),
    STOP_SEQUENCE("stop_sequence");

    private final String id;

    ClaudeStopReason(String id) {
        this.id = id;
    }

    public static ClaudeStopReason fromId(String id) {
        for (ClaudeStopReason reason : values()) {
            if (reason.id.equals(id)) {
                return reason;
            }
        }
        throw new IllegalArgumentException("Unknown stop reason id: " + id);
    }

}
