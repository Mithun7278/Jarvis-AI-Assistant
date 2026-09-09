package com.jarvis.news;

import java.util.List;

/**
 * Interface for News Services
 * 
 * Implementations of this interface can be swapped without changing core logic.
 * Examples: NewsAPI, BBC, Reuters, etc.
 */
public interface NewsService {
    /**
     * Get top news for a category
     * 
     * @param category News category (e.g., "world", "india", "technology")
     * @return A list of news articles
     * @throws Exception if fetching news fails
     */
    List<NewsArticle> getTopNews(String category) throws Exception;
    
    /**
     * Search for news articles
     * 
     * @param query Search query
     * @return A list of matching news articles
     * @throws Exception if search fails
     */
    List<NewsArticle> searchNews(String query) throws Exception;
    
    /**
     * Check if the news service is available
     * 
     * @return true if service is available and configured
     */
    boolean isAvailable();
}
