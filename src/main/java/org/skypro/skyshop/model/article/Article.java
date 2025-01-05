package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final String title;
    private final String content;
    private final UUID id;

    public Article(String title, String content, UUID id) {
        this.title = title;
        this.content = content;
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return title + "\n" + content;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return this.toString();
    }

    @JsonIgnore
    @Override
    public String getType() {
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}

