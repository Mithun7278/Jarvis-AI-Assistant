package com.jarvis.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Weather API Integration
 * Fetches weather information using Open-Meteo API (free, no API key required)
 */
public class WeatherAPI {
    private static final String WEATHER_API_URL = "https://api.open-meteo.com/v1/forecast";
    private static final int CONNECTION_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 5000;

    /**
     * Get weather for a city
     */
    public static Weather getWeather(String latitude, String longitude) {
        try {
            String url = WEATHER_API_URL + "?latitude=" + latitude + 
                        "&longitude=" + longitude + 
                        "&current=temperature_2m,weather_code,wind_speed_10m" +
                        "&timezone=auto";
            
            String response = makeApiRequest(url);
            return parseWeatherResponse(response);
        } catch (Exception e) {
            return new Weather("Error", "Could not fetch weather: " + e.getMessage(), 0);
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
     * Parse weather response
     */
    private static Weather parseWeatherResponse(String jsonResponse) throws Exception {
        JsonObject json = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonObject current = json.getAsJsonObject("current");
        
        String condition = getWeatherCondition(current.get("weather_code").getAsInt());
        double temperature = current.get("temperature_2m").getAsDouble();
        double windSpeed = current.get("wind_speed_10m").getAsDouble();
        
        return new Weather(condition, "Temperature: " + temperature + "°C, Wind: " + windSpeed + " km/h", temperature);
    }

    /**
     * Convert weather code to condition
     */
    private static String getWeatherCondition(int code) {
        switch (code) {
            case 0: return "Clear sky";
            case 1: return "Mainly clear";
            case 2: return "Partly cloudy";
            case 3: return "Overcast";
            case 45: return "Foggy";
            case 48: return "Depositing rime fog";
            case 51: return "Light drizzle";
            case 61: return "Slight rain";
            case 63: return "Moderate rain";
            case 65: return "Heavy rain";
            case 71: return "Slight snow";
            case 73: return "Moderate snow";
            case 75: return "Heavy snow";
            case 80: return "Slight rain showers";
            case 81: return "Moderate rain showers";
            case 82: return "Violent rain showers";
            case 85: return "Slight snow showers";
            case 86: return "Heavy snow showers";
            case 95: return "Thunderstorm";
            default: return "Unknown";
        }
    }

    /**
     * Weather inner class
     */
    public static class Weather {
        private final String condition;
        private final String details;
        private final double temperature;

        public Weather(String condition, String details, double temperature) {
            this.condition = condition;
            this.details = details;
            this.temperature = temperature;
        }

        public String getCondition() {
            return condition;
        }

        public String getDetails() {
            return details;
        }

        public double getTemperature() {
            return temperature;
        }

        @Override
        public String toString() {
            return condition + " - " + details;
        }
    }
}
