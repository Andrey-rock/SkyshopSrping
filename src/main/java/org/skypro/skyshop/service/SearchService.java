package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SearchService {

    private final StorageService storageService;


    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String pattern) {
        return storageService.getSearchableCollection().stream()
                .filter(s -> s.getSearchTerm().contains(pattern))
                .map(SearchResult::fromSearchable)
                .toList();
    }
}
