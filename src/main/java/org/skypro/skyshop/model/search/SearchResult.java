package org.skypro.skyshop.model.search;

import java.util.UUID;

public final class SearchResult {
    private final UUID id;
    private final String name;
    private final String contentType;

    public SearchResult(UUID id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    public static SearchResult fromSearchable(@org.jetbrains.annotations.NotNull Searchable searchable) {
        UUID id = searchable.getId();
        String name = searchable.getSearchTerm();
        String contentType = searchable.getType();
        return new SearchResult(id, name, contentType);
    }
}