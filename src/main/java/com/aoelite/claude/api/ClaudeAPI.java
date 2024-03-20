package com.aoelite.claude.api;

import com.aoelite.claude.data.ClaudeRequest;
import com.aoelite.claude.data.ClaudeResponse;

import java.util.concurrent.CompletableFuture;

public interface ClaudeAPI {

    /**
     * Sends a request to the Claude API and returns the response. This is a blocking call.
     *
     * @param request The request to send.
     * @return The response from the Claude API.
     */
    ClaudeResponse sendRequest(ClaudeRequest request);

    /**
     * Sends a request to the Claude API and returns the response. This is a non-blocking call.
     *
     * @param request The request to send.
     * @return A CompletableFuture that will complete with the response from the Claude API.
     */
    CompletableFuture<ClaudeResponse> sendRequestAsync(ClaudeRequest request);

}
