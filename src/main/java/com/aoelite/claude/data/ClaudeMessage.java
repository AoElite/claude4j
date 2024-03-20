package com.aoelite.claude.data;

import com.aoelite.claude.data.types.ClaudeRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClaudeMessage {

    private ClaudeRole role;
    private String content;

}
