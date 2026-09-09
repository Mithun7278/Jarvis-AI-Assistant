package com.jarvis.news;

import java.time.LocalDateTime;

/**
 * Represents a single news article
 */
public class NewsArticle {
    private final String title;
    private final String description;
    private final String content;
    private final String source;
    private final String url;
    private final LocalDateTime publishedAt;
    private final String category;
    
    public NewsArticle(String title, String description, String content,
                      String source, String url, LocalDateTime publishedAt,
                      String category) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.source = source;
        this.url = url;
        this.publishedAt = publishedAt;
        this.category = category;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getContent() {
        return content;
    }
    
    public String getSource() {
        return source;
    }
    
    public String getUrl() {
        return url;
    }
    
    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }
    
    public String getCategory() {
        return category;
    }
    
    @Override
    public String toString() {
        return "[" + category.toUpperCase() + "] " + title +
               "\nSource: " + source +
               "\nPublished: " + publishedAt +
               "\n" + description;
    }
}
