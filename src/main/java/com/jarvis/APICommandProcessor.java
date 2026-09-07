package com.jarvis;

import com.jarvis.api.JokeGenerator;
import com.jarvis.api.QuotesAPI;
import com.jarvis.api.WeatherAPI;

/**
 * Extended Command Processor with API Integration
 * Handles jokes, quotes, and weather commands
 */
public class APICommandProcessor {
    private TextToSpeech textToSpeech;

    public APICommandProcessor(TextToSpeech textToSpeech) {
        this.textToSpeech = textToSpeech;
    }

    /**
     * Process API commands
     */
    public boolean processAPICommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            return true;
        }

        String lowerCommand = command.toLowerCase();

        // Joke commands
        if (lowerCommand.contains("joke")) {
            return handleJokeCommand(lowerCommand);
        }

        // Quote commands
        if (lowerCommand.contains("quote") || lowerCommand.contains("motivation")) {
            return handleQuoteCommand();
        }

        // Weather commands
        if (lowerCommand.contains("weather")) {
            return handleWeatherCommand(lowerCommand);
        }

        return true;
    }

    /**
     * Handle joke commands
     */
    private boolean handleJokeCommand(String command) {
        try {
            System.out.println("\n[Fetching joke from API...]");
            
            JokeGenerator.Joke joke;
            if (command.contains("programming")) {
                joke = JokeGenerator.getProgrammingJoke();
                System.out.println("[Joke Type]: Programming");
            } else {
                joke = JokeGenerator.getRandomJoke();
            }

            // Display joke
            System.out.println("\n" + joke.toFormattedString());
            
            // Speak joke in Tanglish
            String tanglishJoke = TanglishTranslator.translateToTanglish("joke") + ": " + joke.getSetup();
            textToSpeech.speakTanglish(tanglishJoke);
            System.out.println("\n[Jarvis]: " + tanglishJoke);
            
            return true;
        } catch (Exception e) {
            String errorMsg = "[Error fetching joke]: " + e.getMessage();
            System.out.println(errorMsg);
            textToSpeech.speakTanglish("Enakku joke fetch seigal mudiyale.");
            return true;
        }
    }

    /**
     * Handle quote commands
     */
    private boolean handleQuoteCommand() {
        try {
            System.out.println("\n[Fetching quote from API...]");
            
            QuotesAPI.Quote quote = QuotesAPI.getRandomQuote();
            
            // Display quote
            System.out.println("\n" + quote.toFormattedString());
            
            // Speak quote in Tanglish
            textToSpeech.speakTanglish("Motivation: " + quote.getContent());
            System.out.println("\n[Jarvis]: " + quote.toString());
            
            return true;
        } catch (Exception e) {
            String errorMsg = "[Error fetching quote]: " + e.getMessage();
            System.out.println(errorMsg);
            textToSpeech.speakTanglish("Enakku quote fetch seigal mudiyale.");
            return true;
        }
    }

    /**
     * Handle weather commands
     */
    private boolean handleWeatherCommand(String command) {
        try {
            System.out.println("\n[Fetching weather from API...]");
            
            // Default to Chennai, India coordinates
            String latitude = "13.0827";
            String longitude = "80.2707";
            
            WeatherAPI.Weather weather = WeatherAPI.getWeather(latitude, longitude);
            
            // Display weather
            System.out.println("\n[WEATHER] Chennai, India");
            System.out.println("Condition: " + weather.getCondition());
            System.out.println("Details: " + weather.getDetails());
            
            // Speak weather in Tanglish
            String tanglishWeather = "Weather: " + weather.getCondition() + " " + weather.getDetails();
            textToSpeech.speakTanglish(tanglishWeather);
            System.out.println("\n[Jarvis]: " + weather.toString());
            
            return true;
        } catch (Exception e) {
            String errorMsg = "[Error fetching weather]: " + e.getMessage();
            System.out.println(errorMsg);
            textToSpeech.speakTanglish("Enakku weather fetch seigal mudiyale.");
            return true;
        }
    }

    /**
     * Get help for API commands
     */
    public void printAPICommandsHelp() {
        System.out.println("\n=== API COMMANDS ===");
        System.out.println("  - 'joke' - Get a random joke");
        System.out.println("  - 'programming joke' - Get a programming joke");
        System.out.println("  - 'quote' / 'motivation' - Get a motivational quote");
        System.out.println("  - 'weather' - Get weather information");
        System.out.println("=====================\n");
    }
}
