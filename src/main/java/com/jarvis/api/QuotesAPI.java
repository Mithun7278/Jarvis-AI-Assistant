package com.jarvis.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Quotes API Integration
 * Fetches random motivational quotes
 */
public class QuotesAPI {
    private static final String QUOTES_API_URL = "https://api.quotable.io/random";
    private static final int CONNECTION_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 5000;

    /**
     * Get random quote
     */
    public static Quote getRandomQuote() {
        try {
            String response = makeApiRequest(QUOTES_API_URL);
            return parseQuoteResponse(response);
        } catch (Exception e) {
            return new Quote(
                    "Could not fetch quote",
                    "Error: " + e.getMessage()
            );
        }
    }

    /**
     * Make HTTP GET request
     */
    private static String makeApiRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        try {
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setRequestProperty("User-Agent", "Jarvis-AI-Assistant/1.0");

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("API returned status code: " + responseCode);
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)
            );
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return response.toString();
        } finally {
            connection.disconnect();
        }
    }

    /**
     * Parse quote response
     */
    private static Quote parseQuoteResponse(String jsonResponse) throws Exception {
        JsonObject json = JsonParser.parseString(jsonResponse).getAsJsonObject();
        
        String content = json.has("content") ? json.get("content").getAsString() : "";
        String author = json.has("author") ? json.get("author").getAsString() : "Unknown";
        
        // Clean author name (remove extra characters)
        if (author.contains(",")) {
            author = author.substring(0, author.indexOf(","));
        }
        
        return new Quote(content, author);
    }

    /**
     * Quote inner class
     */
    public static class Quote {
        private final String content;
        private final String author;

        public Quote(String content, String author) {
            this.content = content;
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public String getAuthor() {
            return author;
        }

        @Override
        public String toString() {
            return "\"" + content + "\" - " + author;
        }

        public String toFormattedString() {
            return "[QUOTE]\n" + content + "\n- " + author;
        }
    }
}
