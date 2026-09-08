package com.jarvis;

import com.jarvis.api.JokeGenerator;
import com.jarvis.api.QuotesAPI;
import com.jarvis.api.WeatherAPI;

/**
 * Extended Command Processor with API Integration and TalkBack Support
 * Handles jokes, quotes, and weather commands with accessibility features
 */
public class APICommandProcessor {
    private TextToSpeech textToSpeech;
    private static final String GREETING_PREFIX = "Hi Sir. ";

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
     * Handle joke commands with "Hi Sir" greeting
     */
    private boolean handleJokeCommand(String command) {
        try {
            // Provide accessibility feedback
            textToSpeech.announceScreenEvent("Fetching joke from API");
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
            
            // Speak joke with "Hi Sir" greeting
            String jokeResponse = GREETING_PREFIX + joke.getSetup();
            textToSpeech.speakTanglish(jokeResponse);
            System.out.println("\n[Jarvis]: " + jokeResponse);
            
            return true;
        } catch (Exception e) {
            String errorMsg = "[Error fetching joke]: " + e.getMessage();
            System.out.println(errorMsg);
            textToSpeech.speakTanglish("Hi Sir. Enakku joke fetch seigal mudiyale.");
            textToSpeech.announceScreenEvent("Error while fetching joke");
            return true;
        }
    }

    /**
     * Handle quote commands with "Hi Sir" greeting
     */
    private boolean handleQuoteCommand() {
        try {
            textToSpeech.announceScreenEvent("Fetching quote from API");
            System.out.println("\n[Fetching quote from API...]");
            
            QuotesAPI.Quote quote = QuotesAPI.getRandomQuote();
            
            // Display quote
            System.out.println("\n" + quote.toFormattedString());
            
            // Speak quote with "Hi Sir" greeting
            String quoteResponse = GREETING_PREFIX + quote.getContent();
            textToSpeech.speakTanglish(quoteResponse);
            System.out.println("\n[Jarvis]: " + quoteResponse);
            
            return true;
        } catch (Exception e) {
            String errorMsg = "[Error fetching quote]: " + e.getMessage();
            System.out.println(errorMsg);
            textToSpeech.speakTanglish("Hi Sir. Enakku quote fetch seigal mudiyale.");
            textToSpeech.announceScreenEvent("Error while fetching quote");
            return true;
        }
    }

    /**
     * Handle weather commands with "Hi Sir" greeting
     */
    private boolean handleWeatherCommand(String command) {
        try {
            textToSpeech.announceScreenEvent("Fetching weather from API");
            System.out.println("\n[Fetching weather from API...]");
            
            // Default to Chennai, India coordinates
            String latitude = "13.0827";
            String longitude = "80.2707";
            
            WeatherAPI.Weather weather = WeatherAPI.getWeather(latitude, longitude);
            
            // Display weather
            System.out.println("\n[WEATHER] Chennai, India");
            System.out.println("Condition: " + weather.getCondition());
            System.out.println("Details: " + weather.getDetails());
            
            // Speak weather with "Hi Sir" greeting
            String weatherResponse = GREETING_PREFIX + "Weather: " + weather.getCondition() + ". " + weather.getDetails();
            textToSpeech.speakTanglish(weatherResponse);
            System.out.println("\n[Jarvis]: " + weatherResponse);
            
            return true;
        } catch (Exception e) {
            String errorMsg = "[Error fetching weather]: " + e.getMessage();
            System.out.println(errorMsg);
            textToSpeech.speakTanglish("Hi Sir. Enakku weather fetch seigal mudiyale.");
            textToSpeech.announceScreenEvent("Error while fetching weather");
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
