package com.aoelite.claude.data.stream;

public interface ClaudeStreamHandler {

    void onMessage(ClaudeStreamResponse stream, String message);

}
