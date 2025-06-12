package com.example.breakout;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONArray;

public class CallLLM {

    public static String getResponse(String input) {
        String apiKey = System.getenv("OPENAI_API_KEY"); // Read from environment
        String endpoint = "https://api.openai.com/v1/chat/completions";

        // You can change this system prompt to whatever instructions you want
        String systemPrompt = "You are a greeter for the Breakout game. "
        		+ "As an input you will receive user name and level of difficulty user selected. "
        		+ "Come up with some creative greeting! "
        		+ "Limit your response to 30 tokens."
        		+ "If the user selects Masochist difficulty, MOCK THEM IN A VERY RUDE MANNER.";

        JSONArray messages = new JSONArray()
            .put(new JSONObject()
                .put("role", "system")
                .put("content", systemPrompt))
            .put(new JSONObject()
                .put("role", "user")
                .put("content", input));

        JSONObject payload = new JSONObject();
        payload.put("model", "gpt-3.5-turbo");
        payload.put("messages", messages);
        
        // System.out.println(payload);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endpoint))
            .header("Authorization", "Bearer " + apiKey)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
            .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // You might want to return an error string here for the caller
            return null;
        }
        
        System.out.println(response.body());

        try {
            JSONObject jsonObj = new JSONObject(response.body());
            return jsonObj
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
