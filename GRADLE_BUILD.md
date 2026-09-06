# Jarvis AI Assistant - Gradle Build Guide

## Prerequisites

- **Java 11 or higher**
- **Gradle 8.0+** (optional - use included wrapper)
- **espeak** (for text-to-speech)

## Installing Dependencies

### Linux (Ubuntu/Debian)
```bash
sudo apt-get update
sudo apt-get install espeak openjdk-11-jdk
```

### macOS
```bash
brew install espeak openjdk@11
```

### Windows
1. Download and install [Java 11](https://www.oracle.com/java/technologies/downloads/)
2. Download and install [espeak](http://espeak.sourceforge.net/)
3. Add both to your PATH

## Building the Project

### Using Gradle Wrapper (Recommended - No Gradle installation needed)

**Linux/Mac:**
```bash
./gradlew clean build
```

**Windows:**
```bash
gradlew.bat clean build
```

### Using System Gradle (if installed)

```bash
gradle clean build
```

## Running the Application

### Using Gradle Wrapper

**Linux/Mac:**
```bash
./gradlew runApp
```

**Windows:**
```bash
gradlew.bat runApp
```

### Run Compiled JAR

**Build executable JAR:**

```bash
./gradlew buildFatJar
```

**Run JAR:**

```bash
java -jar build/libs/jarvis-ai-assistant-fat-1.0.0.jar
```

## Common Gradle Tasks

| Task | Description | Command |
|------|-------------|----------|
| `clean` | Remove build directory | `./gradlew clean` |
| `build` | Build project | `./gradlew build` |
| `runApp` | Run application | `./gradlew runApp` |
| `buildFatJar` | Create executable JAR | `./gradlew buildFatJar` |
| `tasks` | List all tasks | `./gradlew tasks` |
| `dependencies` | Show dependencies | `./gradlew dependencies` |
| `compileJava` | Compile Java code | `./gradlew compileJava` |

## Project Structure

```
Jarvis-AI-Assistant/
├── build.gradle                    # Gradle configuration
├── settings.gradle                 # Gradle settings
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties
├── gradlew                         # Gradle wrapper (Linux/Mac)
├── gradlew.bat                     # Gradle wrapper (Windows)
├── src/
│   └── main/
│       └── java/
│           └── com/jarvis/
│               ├── JarvisAssistant.java
│               ├── CommandProcessor.java
│               ├── TextToSpeech.java
│               ├── SpeechRecognition.java
│               └── TanglishTranslator.java
├── build/                          # Generated build files
└── README.md
```

## Understanding Gradle Build

### build.gradle Overview

```gradle
plugins {
    id 'java'           # Java plugin for compilation
    id 'application'    # Application plugin for running
}

group = 'com.jarvis'   # Package group
version = '1.0.0'      # Version

sourceCompatibility = '11'  # Java version

repositories {
    mavenCentral()     # Dependency repository
}

dependencies {
    // Project dependencies
}

application {
    mainClass = 'com.jarvis.JarvisAssistant'  # Entry point
}
```

## Troubleshooting

### Issue: "gradlew: command not found"
**Solution:** Make script executable
```bash
chmod +x gradlew
```

### Issue: "Java 11 not found"
**Solution:** Set JAVA_HOME
```bash
export JAVA_HOME=/path/to/java11  # Linux/Mac
set JAVA_HOME=C:\\path\\to\\java11  # Windows
```

### Issue: Build fails with dependency errors
**Solution:** Clear cache and rebuild
```bash
./gradlew clean --refresh-dependencies build
```

### Issue: "espeak not found"
**Solution:** Install espeak (see Prerequisites section)

## Building a Release JAR

To create a standalone executable JAR:

```bash
./gradlew buildFatJar
```

Output: `build/libs/jarvis-ai-assistant-fat-1.0.0.jar`

## IDE Integration

### IntelliJ IDEA
1. Open project
2. IntelliJ automatically recognizes Gradle configuration
3. Run → Edit Configurations → Add new "Gradle" configuration
4. Set task to `runApp`

### VS Code
1. Install "Gradle for Java" extension
2. Open project
3. VS Code recognizes Gradle configuration
4. Run tasks from VS Code terminal

### Eclipse
1. Install Buildship (Gradle Integration)
2. Right-click project → Configure → Convert to Gradle Project
3. Run as Gradle Application

## Gradle Wrapper Benefits

✅ No Gradle installation required  
✅ Consistent Gradle version across team  
✅ Automatic Gradle download  
✅ Works on all operating systems  
✅ Best practice for CI/CD pipelines  

## More Information

- [Official Gradle Documentation](https://docs.gradle.org)
- [Gradle Java Plugin](https://docs.gradle.org/current/userguide/java_plugin.html)
- [Gradle Application Plugin](https://docs.gradle.org/current/userguide/application_plugin.html)

---

**For more help:** Check README.md or run `./gradlew help`
