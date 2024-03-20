package com.aoelite.claude.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClaudeUsage {

    private int input_tokens;
    private int output_tokens;

}
