package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    //Нет объектов в storageService
    @Test
    void whenThereAreNoObject() {
        Mockito.when(storageService.getSearchableCollection()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(Collections.emptyList(), searchService.search("test"));
    }

    //Есть объекты в storageService, но нет подходящего
    @Test
    void whenThereAreObjectsButNoSuitable() {
        List<Product> products = new ArrayList<>();
        UUID id = UUID.randomUUID();
        Product p1 = new FixPriceProduct("Масло", id);
        Product p2 = new SimpleProduct("Капуста", 30, id);
        Product p3 = new DiscountedProduct("Чай", 100, 10, id);
        Product p4 = new SimpleProduct("Кофе", 350, id);
        Product p5 = new SimpleProduct("Пельмени", 280, id);
        Product p6 = new DiscountedProduct("Торт", 600, 5, id);
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);
        products.add(p6);


        Mockito.when(storageService.getSearchableCollection()).thenReturn(Collections.unmodifiableList(products));
        Assertions.assertEquals(Collections.emptyList(), searchService.search("яблоко"));
    }

    //Есть объекты в storageService, и есть подходящий
    @Test
    void whenThereAreProductsAndThereSuitable() {
        List<Product> products = new ArrayList<>();
        UUID id = UUID.randomUUID();
        Product p1 = new FixPriceProduct("Масло", id);
        Product p2 = new SimpleProduct("Капуста", 30, id);
        Product p3 = new DiscountedProduct("Чай", 100, 10, id);
        Product p4 = new SimpleProduct("Кофе", 350, id);
        Product p5 = new SimpleProduct("Пельмени", 280, id);
        Product p6 = new DiscountedProduct("Торт", 600, 5, id);
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);
        products.add(p6);
        List<SearchResult> result = new ArrayList<>();
        result.add(new SearchResult(id, "Масло", "PRODUCT"));


        Mockito.when(storageService.getSearchableCollection()).thenReturn(Collections.unmodifiableList(products));
        Assertions.assertEquals(result, searchService.search("Масло"));
    }

    @Test
    void whenThereAreArticlesAndThereSuitable() {
        List<Article> products = new ArrayList<>();
        UUID id = UUID.randomUUID();
        Article a1 = new Article("Чай. История.", "Чай китайский и индийский", id);
        Article a2 = new Article("Пельмени", "Пельмени в русской традиции", id);
        Article a3 = new Article("Капуста", "Десять блюд из капусты", id);
        Article a4 = new Article("Кофе. История.", "Кофе: арабика и робуста", id);
        Article a6 = new Article("Арбуз. История.", "Арбуз: тут что-то про арбуз", id);
        Article a5 = new Article("Чай черный", "Чай бла бла бла. Чай бла бла бла...", id);
        products.add(a1);
        products.add(a2);
        products.add(a3);
        products.add(a4);
        products.add(a5);
        products.add(a6);
        List<SearchResult> result = new ArrayList<>();
        result.add(new SearchResult(id, a6.toString(), "ARTICLE"));


        Mockito.when(storageService.getSearchableCollection()).thenReturn(Collections.unmodifiableList(products));
        Assertions.assertEquals(result, searchService.search("Арбуз"));
    }
}
