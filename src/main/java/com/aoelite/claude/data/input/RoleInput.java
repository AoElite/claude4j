package com.aoelite.claude.data.input;

import com.aoelite.claude.data.types.ClaudeRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class RoleInput {

    private final ClaudeRole role;
    @Singular private final List<ClaudeMessage> messages;


}
