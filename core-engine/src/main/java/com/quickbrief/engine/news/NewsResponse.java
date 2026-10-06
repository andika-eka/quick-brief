package com.quickbrief.engine.news;

import java.util.List;

public record NewsResponse(
    String status,
    Integer totalResults,
    List<Article> articles
) {}
