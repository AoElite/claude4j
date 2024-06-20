package com.aoelite.claude;

import com.aoelite.claude.api.ClaudeAPI;
import com.aoelite.claude.data.ClaudeRequest;
import com.aoelite.claude.data.ClaudeResponse;
import com.aoelite.claude.data.ClaudeUsage;
import com.aoelite.claude.data.input.ClaudeMessage;
import com.aoelite.claude.data.input.RoleInput;
import com.aoelite.claude.data.types.ClaudeRole;
import com.aoelite.claude.data.types.ClaudeStopReason;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ClaudeClient implements ClaudeAPI {
    private final String api_key;
    private final HttpClient httpClient;
    private final static String API_URL = "https://api.anthropic.com/v1/messages";
    private static final URI URI = createURI();
    private final long timeoutInMs;

    public ClaudeClient(String api_key, HttpClient client, long timeoutInMs) {
        this.api_key = api_key;
        this.httpClient = client;
        this.timeoutInMs = timeoutInMs;
    }

    public ClaudeClient(String api_key) {
        this.api_key = api_key;
        this.httpClient = HttpClient.newBuilder().build();
        this.timeoutInMs = 10000L;
    }

    @Override
    public @Nullable ClaudeResponse sendRequest(ClaudeRequest request) {
        if (request.getInputs().isEmpty()) throw new IllegalArgumentException("Inputs cannot be empty");
        JsonObject jsonResponse = sendRequestAndGetJson(request);
        if (jsonResponse == null) return null;
        return parseResponse(jsonResponse);
    }

    @Override
    public CompletableFuture<ClaudeResponse> sendRequestAsync(ClaudeRequest request) {
        return CompletableFuture.supplyAsync(() -> sendRequest(request));
    }

    private ClaudeResponse parseResponse(JsonObject json) {
        String id = json.get("id").getAsString();
        String type = json.get("type").getAsString();
        String roleId = json.get("role").getAsString();
        ClaudeRole role = ClaudeRole.fromId(roleId);
        JsonArray content = json.getAsJsonArray("content");
        List<String> contentList = new ArrayList<>();
        for (JsonElement jsonElement : content) {
            String text = jsonElement.getAsJsonObject().get("text").getAsString();
            contentList.add(text);
        }
        String model = json.get("model").getAsString();
        ClaudeStopReason stop_reason = ClaudeStopReason.fromId(json.get("stop_reason").getAsString());
        JsonElement stop_sequence_element = json.get("stop_sequence");
        String stop_sequence = stop_sequence_element.isJsonNull() ? null : stop_sequence_element.getAsString();
        JsonObject usageObject = json.getAsJsonObject("usage");
        int input_tokens = usageObject.get("input_tokens").getAsInt();
        int output_tokens = usageObject.get("output_tokens").getAsInt();
        ClaudeUsage usage = new ClaudeUsage(input_tokens, output_tokens);
        return new ClaudeResponse(id, type, role, contentList, model, stop_reason, stop_sequence, usage);
    }

    private JsonObject sendRequestAndGetJson(ClaudeRequest request) {
        JsonObject jsonObject = generateJsonRequest(request);
        String json = jsonObject.toString();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("x-api-key", api_key)
                .header("anthropic-version", "2023-06-01")
                .header("content-type", "application/json")
                .timeout(Duration.of(timeoutInMs, ChronoUnit.MILLIS))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.err.println("Response code: " + response.statusCode() + " Response: " + response.body());
                return null;
            }
            JsonElement element = JsonParser.parseString(response.body());
            return element.getAsJsonObject();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JsonObject generateJsonRequest(ClaudeRequest request) {
        JsonObject object = new JsonObject();
        object.addProperty("model", request.getModel().getId());
        object.addProperty("max_tokens", request.getMax_tokens());
        object.addProperty("temperature", request.getTemperature());

        if (request.getStop_sequences() != null && !request.getStop_sequences().isEmpty()) {
            JsonArray stopSequences = new JsonArray();
            for (String stopSequence : request.getStop_sequences()) {
                stopSequences.add(stopSequence);
            }
            object.add("stop_sequences", stopSequences);
        }

        if (request.getSystem() != null && !request.getSystem().isEmpty())
            object.addProperty("system", request.getSystem());
        JsonArray messages = new JsonArray();
        for (RoleInput input : request.getInputs()) {
            //
            JsonObject messageObject = new JsonObject();
            messageObject.addProperty("role", input.getRole().getId());
            //
            JsonArray messageContent = new JsonArray();
            for (ClaudeMessage content : input.getMessages()) {
                messageContent.add(content.toJsonObject());
            }
            //
            messageObject.add("content", messageContent);
            messages.add(messageObject);
        }

        object.add("messages", messages);
        return object;
    }

    private static URI createURI() {
        try {
            return new URI(ClaudeClient.API_URL);
        } catch (java.net.URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
