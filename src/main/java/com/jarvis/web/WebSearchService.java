package com.jarvis.web;

import java.util.List;

/**
 * Interface for Web Search Services
 * 
 * Implementations of this interface can be swapped without changing core logic.
 * Examples: Google Search API, Bing Search, DuckDuckGo, etc.
 */
public interface WebSearchService {
    /**
     * Search the web for a query
     * 
     * @param query The search query
     * @return A list of search results
     * @throws Exception if search fails
     */
    List<SearchResult> search(String query) throws Exception;
    
    /**
     * Check if the web search service is available
     * 
     * @return true if service is available and configured
     */
    boolean isAvailable();
}
