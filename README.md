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
✅ **Gradle Build** - Modern build system with Gradle wrapper  

## Quick Start

### Prerequisites
- Java 11 or higher
- espeak (for text-to-speech)

### Installation & Build

```bash
# Clone repository
git clone https://github.com/Mithun7278/Jarvis-AI-Assistant.git
cd Jarvis-AI-Assistant

# Build with Gradle
./gradlew build

# Run application
./gradlew runApp
```

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

### Linux/Mac
```bash
# Install Java 11
sudo apt-get install openjdk-11-jdk        # Ubuntu/Debian
brew install openjdk@11                    # Mac

# Install espeak
sudo apt-get install espeak                # Ubuntu/Debian
brew install espeak                        # Mac

# Clone and build
git clone https://github.com/Mithun7278/Jarvis-AI-Assistant.git
cd Jarvis-AI-Assistant
./gradlew build
./gradlew runApp
```

### Windows
```bash
# Install Java 11 from: https://www.oracle.com/java/technologies/downloads/
# Install espeak from: http://espeak.sourceforge.net/
# Add both to PATH

# Clone and build
git clone https://github.com/Mithun7278/Jarvis-AI-Assistant.git
cd Jarvis-AI-Assistant
gradlew.bat build
gradlew.bat runApp
```

## Project Structure

```
Jarvis-AI-Assistant/
├── build.gradle                     # Gradle configuration
├── settings.gradle                  # Gradle settings
├── gradle/
│   └── wrapper/                     # Gradle wrapper files
├── gradlew & gradlew.bat           # Gradle wrapper scripts
├── src/
│   └── main/
│       └── java/
│           └── com/jarvis/
│               ├── JarvisAssistant.java       # Main class
│               ├── CommandProcessor.java      # Command processing
│               ├── TextToSpeech.java         # TTS engine
│               ├── SpeechRecognition.java    # Speech input
│               └── TanglishTranslator.java   # Tanglish translation
├── GRADLE_BUILD.md                  # Gradle build guide
├── README.md                        # This file
└── .gitignore
```

## Usage Example

```
╔═══════════════════════════════════════════════════════════╗
║                                                           ║
║   WELCOME TO JARVIS AI ASSISTANT v1.0              ║
║        (With Tanglish Language Support)             ║
║                                                           ║
║ A Complete Java-based AI Assistant                      ║
║ Developed with Tamil/Tanglish Communication             ║
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

### Common Tanglish Phrases
- **Vanakkam** - Hello/Hi
- **Nandri** - Thank you
- **Poi varuvom** - Goodbye
- **Ama** - Yes
- **Illai** - No
- **Yeppadi irukkai** - How are you?
- **Naan sari irukren** - I am fine
- **Mannikanum** - Sorry
- **Enakku puriyale** - I don't understand

## Gradle Tasks

### Common Commands

```bash
# Clean build
./gradlew clean

# Build project
./gradlew build

# Run application
./gradlew runApp

# Build executable JAR
./gradlew buildFatJar

# List all tasks
./gradlew tasks

# Show dependencies
./gradlew dependencies
```

**For detailed Gradle guide, see [GRADLE_BUILD.md](GRADLE_BUILD.md)**

## Building Executable JAR

```bash
# Create fat JAR (all dependencies included)
./gradlew buildFatJar

# Run the JAR
java -jar build/libs/jarvis-ai-assistant-fat-1.0.0.jar
```

## Architecture

### Core Components

1. **JarvisAssistant** - Main orchestrator and entry point
2. **CommandProcessor** - Parses and executes user commands
3. **TextToSpeech** - Converts text to speech in Tanglish
4. **SpeechRecognition** - Captures and processes user input
5. **TanglishTranslator** - Translates English commands to Tanglish

### Technology Stack

- **Language:** Java 11+
- **Build Tool:** Gradle 8.0+
- **TTS:** espeak
- **Dependencies:** Google Cloud APIs, Gson, SLF4J

## Error Handling

✅ Graceful error handling for all operations  
✅ Fallback mechanisms for TTS failures  
✅ Input validation for all commands  
✅ Cross-platform compatibility checks  
✅ Detailed error messages and logging  

## Future Enhancements

- 🚀 Real-time voice recognition using Google Cloud API
- 🚀 Weather integration
- 🚀 Music player control
- 🚀 Email support
- 🚀 Calendar integration
- 🚀 Machine Learning for command learning
- 🚀 Multi-language support (Hindi, Telugu, Kannada)
- 🚀 Smart home automation
- 🚀 Mobile app integration

## Troubleshooting

### Issue: espeak not found
**Solution:** Install espeak for your OS (see Installation section)

### Issue: "gradlew: command not found"
**Solution:** Make executable on Linux/Mac
```bash
chmod +x gradlew
```

### Issue: No audio output
**Solution:** Check system volume and espeak installation

### Issue: Commands not recognized
**Solution:** Type commands clearly and refer to help menu by typing 'help'

### Issue: Build fails
**Solution:** Clear cache and rebuild
```bash
./gradlew clean --refresh-dependencies build
```

## IDE Support

✅ **IntelliJ IDEA** - Built-in Gradle support  
✅ **VS Code** - With Gradle for Java extension  
✅ **Eclipse** - With Buildship plugin  
✅ **NetBeans** - Built-in Gradle support  

## License

MIT License - Feel free to use, modify, and distribute

## Author

**Mithun7278** - GitHub Developer

## Contributing

Contributions are welcome! Please fork the repository and submit pull requests.

See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## Support

For issues, questions, or suggestions, please create an issue on GitHub.

---

**Made with ❤️ in Java | Powered by Tanglish & Gradle** 🎙️
