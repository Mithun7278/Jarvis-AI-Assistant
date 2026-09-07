package com.jarvis.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Joke Generator using Official Joke API
 * Fetches random jokes from official-joke-api.appspot.com
 */
public class JokeGenerator {
    private static final String JOKE_API_URL = "https://official-joke-api.appspot.com/random_joke";
    private static final String PROGRAMMING_JOKE_API = "https://official-joke-api.appspot.com/jokes/programming/random";
    private static final int CONNECTION_TIMEOUT = 5000; // 5 seconds
    private static final int READ_TIMEOUT = 5000;

    /**
     * Fetch a random joke from the API
     */
    public static Joke getRandomJoke() {
        try {
            String response = makeApiRequest(JOKE_API_URL);
            return parseJokeResponse(response);
        } catch (Exception e) {
            return new Joke(
                    "Error",
                    "Could not fetch joke from API. " + e.getMessage(),
                    "offline"
            );
        }
    }

    /**
     * Fetch a random programming joke
     */
    public static Joke getProgrammingJoke() {
        try {
            String response = makeApiRequest(PROGRAMMING_JOKE_API);
            return parseJokeResponse(response);
        } catch (Exception e) {
            return new Joke(
                    "Error",
                    "Could not fetch programming joke. " + e.getMessage(),
                    "offline"
            );
        }
    }

    /**
     * Make HTTP GET request to API
     */
    private static String makeApiRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        try {
            // Set connection parameters
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setRequestProperty("User-Agent", "Jarvis-AI-Assistant/1.0");

            // Check response code
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("API returned status code: " + responseCode);
            }

            // Read response
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
     * Parse JSON response and create Joke object
     */
    private static Joke parseJokeResponse(String jsonResponse) throws Exception {
        try {
            JsonObject json = JsonParser.parseString(jsonResponse).getAsJsonObject();
            
            String type = json.has("type") ? json.get("type").getAsString() : "general";
            String setup = json.has("setup") ? json.get("setup").getAsString() : "";
            String punchline = json.has("punchline") ? json.get("punchline").getAsString() : "";
            String id = json.has("id") ? json.get("id").getAsString() : "unknown";

            return new Joke(setup, punchline, type);
        } catch (Exception e) {
            throw new Exception("Failed to parse joke response: " + e.getMessage());
        }
    }

    /**
     * Get joke with retry mechanism
     */
    public static Joke getRandomJokeWithRetry(int maxRetries) {
        for (int i = 0; i < maxRetries; i++) {
            try {
                Joke joke = getRandomJoke();
                if (!joke.getType().equals("offline")) {
                    return joke;
                }
            } catch (Exception e) {
                if (i == maxRetries - 1) {
                    return new Joke(
                            "Error",
                            "Could not fetch joke after " + maxRetries + " attempts.",
                            "offline"
                    );
                }
                try {
                    Thread.sleep(1000); // Wait 1 second before retry
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        return new Joke("Error", "Failed to fetch joke", "offline");
    }

    /**
     * Inner class to represent a Joke
     */
    public static class Joke {
        private final String setup;
        private final String punchline;
        private final String type;

        public Joke(String setup, String punchline, String type) {
            this.setup = setup;
            this.punchline = punchline;
            this.type = type;
        }

        public String getSetup() {
            return setup;
        }

        public String getPunchline() {
            return punchline;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return setup + "\n" + punchline;
        }

        /**
         * Get joke in formatted Tanglish style
         */
        public String toFormattedString() {
            return "[" + type.toUpperCase() + " JOKE]\n" +
                    "Setup: " + setup + "\n" +
                    "Punchline: " + punchline;
        }
    }
}
