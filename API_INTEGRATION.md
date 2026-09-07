# API Integration Guide for Jarvis AI Assistant

## Overview

Jarvis AI Assistant now supports integration with multiple external APIs:

1. **Official Joke API** - Random jokes and programming jokes
2. **Quotable API** - Motivational quotes
3. **Open-Meteo Weather API** - Real-time weather information

## APIs Used

### 1. Official Joke API

**Endpoint:** `https://official-joke-api.appspot.com/random_joke`

**Features:**
- Free, no API key required
- Random jokes
- Programming jokes
- Response time: < 1 second

**Example Response:**
```json
{
  "type": "general",
  "setup": "Why don't scientists trust atoms?",
  "punchline": "Because they make up everything!",
  "id": 1
}
```

**Usage in Jarvis:**
```
> joke
[GENERAL JOKE]
Setup: Why don't scientists trust atoms?
Punchline: Because they make up everything!
```

### 2. Quotable API

**Endpoint:** `https://api.quotable.io/random`

**Features:**
- Free, no API key required
- Thousands of quotes
- Author information
- Response time: < 1 second

**Example Response:**
```json
{
  "content": "The only way to do great work is to love what you do.",
  "author": "Steve Jobs",
  "tags": ["work", "motivation"]
}
```

**Usage in Jarvis:**
```
> quote
[QUOTE]
The only way to do great work is to love what you do.
- Steve Jobs
```

### 3. Open-Meteo Weather API

**Endpoint:** `https://api.open-meteo.com/v1/forecast`

**Features:**
- Free, no API key required
- Real-time weather data
- 40+ weather codes
- Supports any latitude/longitude

**Default Location:** Chennai, India (13.0827°N, 80.2707°E)

**Example Response:**
```json
{
  "current": {
    "temperature_2m": 28.5,
    "weather_code": 0,
    "wind_speed_10m": 12.5
  }
}
```

**Usage in Jarvis:**
```
> weather
[WEATHER] Chennai, India
Condition: Clear sky
Details: Temperature: 28.5°C, Wind: 12.5 km/h
```

## Available Commands

### Joke Commands

```
joke                    # Get a random joke
programming joke        # Get a programming joke
```

### Quote Commands

```
quote                   # Get a motivational quote
motivation              # Get a motivational quote
```

### Weather Commands

```
weather                 # Get current weather for Chennai
```

## Error Handling

All API requests include:
- ✅ Connection timeout (5 seconds)
- ✅ Read timeout (5 seconds)
- ✅ HTTP error handling
- ✅ JSON parsing error handling
- ✅ Network error recovery
- ✅ Retry mechanism (for jokes)
- ✅ Graceful fallback responses

## Classes Involved

### JokeGenerator.java
```java
public class JokeGenerator {
    // Fetch random jokes
    public static Joke getRandomJoke()
    public static Joke getProgrammingJoke()
    public static Joke getRandomJokeWithRetry(int maxRetries)
}
```

### QuotesAPI.java
```java
public class QuotesAPI {
    // Fetch motivational quotes
    public static Quote getRandomQuote()
}
```

### WeatherAPI.java
```java
public class WeatherAPI {
    // Fetch weather information
    public static Weather getWeather(String latitude, String longitude)
}
```

### APICommandProcessor.java
```java
public class APICommandProcessor {
    // Process API-related commands
    public boolean processAPICommand(String command)
    
    // Handle specific API commands
    private boolean handleJokeCommand(String command)
    private boolean handleQuoteCommand()
    private boolean handleWeatherCommand(String command)
}
```

## Testing APIs

### Test Commands

```bash
# Test joke API
> joke
> programming joke

# Test quote API
> quote
> motivation

# Test weather API
> weather
```

### Expected Output

All API responses are:
- ✅ Spoken in Tanglish
- ✅ Displayed on console
- ✅ Properly formatted
- ✅ Error-safe

## Customization

### Change Weather Location

Edit `WeatherAPI.java`:

```java
// Default to Chennai, India
String latitude = "13.0827";
String longitude = "80.2707";

// Change to your location
String latitude = "40.7128";  // New York
String longitude = "-74.0060";
```

### Add More Jokes Types

Edit `JokeGenerator.java`:

```java
public static Joke getKnockKnockJoke() {
    String url = "https://official-joke-api.appspot.com/jokes/knock-knock/random";
    // Implementation
}
```

### Add More Quote Categories

Edit `QuotesAPI.java`:

```java
public static Quote getQuoteByTag(String tag) {
    String url = "https://api.quotable.io/random?tags=" + tag;
    // Implementation
}
```

## Dependencies

Ensure these are in your `build.gradle`:

```gradle
implementation 'com.google.code.gson:gson:2.10.1'
```

## Network Requirements

- ✅ Active internet connection
- ✅ No proxy issues
- ✅ No firewall blocking external APIs
- ✅ DNS resolution working

## Performance

- **Joke API:** ~200-500ms
- **Quote API:** ~200-500ms
- **Weather API:** ~300-800ms
- **Timeouts:** 5 seconds per request

## Troubleshooting

### "Could not fetch joke from API"
- Check internet connection
- Verify API is accessible
- Check firewall settings

### "Connection timeout"
- Increase timeout value in API classes
- Check network speed
- Try again after some time

### "JSON parsing error"
- API response format may have changed
- Check API documentation
- Update parsing logic

## Future Enhancements

- 🚀 Cache API responses
- 🚀 Add more APIs (news, trivia, etc.)
- 🚀 Support location-based weather
- 🚀 Add offline fallback data
- 🚀 Implement rate limiting
- 🚀 Add custom API support

---

**For more information, see README.md**
