# Jarvis AI Assistant

A complete Java-based Jarvis AI Assistant with **Tanglish (Tamil + English)** language support.

## Features

✅ **Voice Recognition** - Listen to user commands  
✅ **Text-to-Speech** - Speak responses in Tanglish  
✅ **Tanglish Support** - Full Tamil language communication  
✅ **Command Processing** - Execute various AI commands  
✅ **Browser Integration** - Open websites (Google, YouTube)  
✅ **Time & Date** - Get current time and date in Tanglish  
✅ **Help System** - Built-in help menu  
✅ **Cross-Platform** - Works on Windows, Mac, Linux  

## Supported Commands

### Tanglish/English Commands

| Command | Tanglish | Description |
|---------|----------|-------------|
| time | neram | Get current time |
| date | naal | Get current date |
| hello | vanakkam | Greet Jarvis |
| your name | peru | Ask Jarvis's name |
| thank you | nandri | Say thank you |
| how are you | eppadi | Ask how Jarvis is |
| open google | Google-ai thira | Open Google |
| open youtube | YouTube-ai thira | Open YouTube |
| help | | Show help menu |
| bye/exit | varuvom | Exit Jarvis |

## Installation

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- `espeak` (for text-to-speech)

### Linux/Mac
```bash
# Install espeak
sudo apt-get install espeak        # Ubuntu/Debian
brew install espeak                # Mac

# Clone repository
git clone https://github.com/Mithun7278/Jarvis-AI-Assistant.git
cd Jarvis-AI-Assistant

# Build with Maven
mvn clean install

# Run
java -jar target/jarvis-ai-assistant-1.0.0.jar
```

### Windows
```bash
# Install espeak from: http://espeak.sourceforge.net/
# Add espeak to PATH

# Clone repository
git clone https://github.com/Mithun7278/Jarvis-AI-Assistant.git
cd Jarvis-AI-Assistant

# Build with Maven
mvn clean install

# Run
java -jar target/jarvis-ai-assistant-1.0.0.jar
```

## Project Structure

```
Jarvis-AI-Assistant/
├── src/
│   └── main/
│       └── java/
│           └── com/jarvis/
│               ├── JarvisAssistant.java      # Main class
│               ├── CommandProcessor.java     # Command processing
│               ├── TextToSpeech.java        # TTS engine
│               ├── SpeechRecognition.java   # Speech input
│               └── TanglishTranslator.java  # Tanglish translation
├── pom.xml                                   # Maven config
└── README.md                                 # This file
```

## Usage Example

```
╔═══════════════════════════════════════════════════════════╗
║                                                           ║
║         WELCOME TO JARVIS AI ASSISTANT v1.0              ║
║              (With Tanglish Language Support)             ║
║                                                           ║
║  A Complete Java-based AI Assistant                      ║
║  Developed with Tamil/Tanglish Communication             ║
║                                                           ║
╚═══════════════════════════════════════════════════════════╝

[Initializing Jarvis AI Assistant...]
[Systems loaded successfully!]
[Ready to assist you!]

[Jarvis]: Vanakkam! Naan Jarvis. Yeppadi irukkai?

[Listening...] > hello
[You said]: hello
[Jarvis]: Vanakkam! Yeppadi irukkai? Enakku yenna thuli seigal patrum?

[Listening...] > time
[You said]: time
[Jarvis]: Idhu 14:30:45

[Listening...] > bye
[You said]: bye
[Jarvis]: Poi varuvom! Pudu naal tappu solluvom!
[Shutting down Jarvis Assistant...]
```

## Tanglish Language Support

Jarvis communicates in **Tanglish** - a mix of Tamil and English that helps users interact naturally.

### Tanglish Phrases
- **Vanakkam** - Hello/Hi
- **Nandri** - Thank you
- **Poi varuvom** - Goodbye
- **Ama** - Yes
- **Illai** - No
- **Yeppadi irukkai** - How are you?
- **Naan sari irukren** - I am fine
- **Mannikanum** - Sorry

## Architecture

### Core Components

1. **JarvisAssistant** - Main orchestrator
2. **CommandProcessor** - Parses and executes commands
3. **TextToSpeech** - Converts text to speech (Tanglish)
4. **SpeechRecognition** - Captures user input
5. **TanglishTranslator** - Translates English to Tanglish

## Error Handling

✅ Graceful error handling for all operations  
✅ Fallback mechanisms for TTS failures  
✅ Input validation for all commands  
✅ Cross-platform compatibility checks  

## Future Enhancements

- 🚀 Real-time voice recognition using Google Cloud API
- 🚀 Weather integration
- 🚀 Music player control
- 🚀 Email support
- 🚀 Calendar integration
- 🚀 Machine Learning for command learning
- 🚀 Multi-language support (Hindi, Telugu, Kannada)
- 🚀 Smart home automation

## Troubleshooting

### Issue: espeak not found
**Solution:** Install espeak for your OS (see Installation section)

### Issue: No audio output
**Solution:** Check system volume and espeak installation

### Issue: Commands not recognized
**Solution:** Type commands clearly and refer to help menu

## License

MIT License - Feel free to use, modify, and distribute

## Author

**Mithun7278** - GitHub Developer

## Contributing

Contributions are welcome! Please fork the repository and submit pull requests.

## Support

For issues, questions, or suggestions, please create an issue on GitHub.

---

**Made with ❤️ in Java | Powered by Tanglish** 🎙️
