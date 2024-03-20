package com.aoelite.claude.data;

import com.aoelite.claude.data.types.ClaudeRole;
import com.aoelite.claude.data.types.ClaudeStopReason;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ClaudeResponse {

    private String id;
    private String type;
    private ClaudeRole role;
    private List<String> content;
    private String model;
    private ClaudeStopReason stop_reason;
    private String stop_sequence;
    private ClaudeUsage usage;

}
