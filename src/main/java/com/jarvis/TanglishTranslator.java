package com.jarvis;

import java.util.HashMap;
import java.util.Map;

/**
 * Tanglish Language Translator
 * Converts English to Tamil script written in English (Tanglish)
 */
public class TanglishTranslator {
    private static final Map<String, String> TANGLISH_MAP = new HashMap<>();

    static {
        // Common Tanglish phrases
        TANGLISH_MAP.put("hello", "Vanakkam");
        TANGLISH_MAP.put("hi", "Saravana vanakkam");
        TANGLISH_MAP.put("good morning", "Kalai vanakkam");
        TANGLISH_MAP.put("good afternoon", "Pasala vanakkam");
        TANGLISH_MAP.put("good evening", "Malai vanakkam");
        TANGLISH_MAP.put("thank you", "Nandri");
        TANGLISH_MAP.put("thank", "Nandri");
        TANGLISH_MAP.put("bye", "Poi varuvom");
        TANGLISH_MAP.put("goodbye", "Poi varuvom");
        TANGLISH_MAP.put("yes", "Ama");
        TANGLISH_MAP.put("no", "Illai");
        TANGLISH_MAP.put("okay", "Seri");
        TANGLISH_MAP.put("ok", "Seri");
        TANGLISH_MAP.put("what is your name", "Unakku yenna peru");
        TANGLISH_MAP.put("my name is", "En peru");
        TANGLISH_MAP.put("time", "Neram");
        TANGLISH_MAP.put("what time", "Yenna neram");
        TANGLISH_MAP.put("date", "Naal");
        TANGLISH_MAP.put("weather", "Kaalamanam");
        TANGLISH_MAP.put("sorry", "Mannikanum");
        TANGLISH_MAP.put("please", "Thayavum");
        TANGLISH_MAP.put("help", "Thuli");
        TANGLISH_MAP.put("search", "Thedi parungal");
        TANGLISH_MAP.put("open google", "Google-ai thira");
        TANGLISH_MAP.put("open youtube", "YouTube-ai thira");
        TANGLISH_MAP.put("play music", "Isai aayiram");
        TANGLISH_MAP.put("stop", "Nirutpu");
        TANGLISH_MAP.put("i am sorry", "Yen mannikanum");
        TANGLISH_MAP.put("how are you", "Nee eppadi irukkai");
        TANGLISH_MAP.put("i am fine", "Naan sari irukren");
        TANGLISH_MAP.put("jarvis", "Jarvis");
        TANGLISH_MAP.put("assistant", "Thuli");
    }

    /**
     * Translate English text to Tanglish
     */
    public static String translateToTanglish(String englishText) {
        if (englishText == null || englishText.trim().isEmpty()) {
            return "";
        }

        String lowerText = englishText.toLowerCase().trim();

        // Check for exact matches first
        if (TANGLISH_MAP.containsKey(lowerText)) {
            return TANGLISH_MAP.get(lowerText);
        }

        // Check for partial matches
        for (Map.Entry<String, String> entry : TANGLISH_MAP.entrySet()) {
            if (lowerText.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        // If no translation found, return original text
        return englishText;
    }

    /**
     * Get Tanglish response based on command
     */
    public static String getTanglishResponse(String command) {
        String lower = command.toLowerCase();

        if (lower.contains("time")) {
            return "Idhu ";
        } else if (lower.contains("date")) {
            return "Inru ";
        } else if (lower.contains("thank")) {
            return "Nandri! Vaalkai";
        } else if (lower.contains("hello") || lower.contains("hi")) {
            return "Vanakkam! Yeppadi irukkai?";
        } else if (lower.contains("bye") || lower.contains("exit")) {
            return "Poi varuvom! Pudu naal tappu solluvom!";
        } else if (lower.contains("help")) {
            return "Enakku thuli seigal patrum. Yenna vendina sollu?";
        }

        return "Enakku puriyale. Repati sollu.";
    }
}
