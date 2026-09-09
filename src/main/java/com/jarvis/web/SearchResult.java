package com.jarvis.web;

/**
 * Represents a single web search result
 */
public class SearchResult {
    private final String title;
    private final String url;
    private final String snippet;
    private final long timestamp;
    
    public SearchResult(String title, String url, String snippet) {
        this.title = title;
        this.url = url;
        this.snippet = snippet;
        this.timestamp = System.currentTimeMillis();
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getSnippet() {
        return snippet;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString() {
        return "[" + title + "]\n" +
               "URL: " + url + "\n" +
               "Snippet: " + snippet;
    }
}
