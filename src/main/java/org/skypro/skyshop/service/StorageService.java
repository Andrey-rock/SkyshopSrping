package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> storageProduct;
    private final Map<UUID, Article> storageArticle;

    public StorageService() {
        storageProduct = new HashMap<>();
        storageArticle = new HashMap<>();
        initialStorageService();
    }

    private void initialStorageService() {
        UUID[] ids = new UUID[20];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = UUID.randomUUID();
        }
        //Создание продуктов
        Product p1 = new FixPriceProduct("Масло", ids[0]);
        Product p2 = new SimpleProduct("Капуста", 30, ids[1]);
        Product p3 = new DiscountedProduct("Чай", 100, 10, ids[2]);
        Product p4 = new SimpleProduct("Кофе", 350, ids[3]);
        Product p5 = new SimpleProduct("Пельмени", 280, ids[4]);
        Product p6 = new DiscountedProduct("Торт", 600, 5, ids[5]);
        System.out.println();

        //Создание статей
        Article a1 = new Article("Чай. История.", "Чай китайский и индийский", ids[6]);
        Article a2 = new Article("Пельмени", "Пельмени в русской традиции", ids[7]);
        Article a3 = new Article("Капуста", "Десять блюд из капусты", ids[8]);
        Article a4 = new Article("Кофе. История.", "Кофе: арабика и робуста", ids[9]);
        Article a6 = new Article("Арбуз. История.", "Арбуз: тут что-то про арбуз", ids[10]);
        Article a5 = new Article("Чай черный", "Чай бла бла бла. Чай бла бла бла...", ids[11]);
        Article a7 = new Article("Чай зелёный", "Тут очень очень большой текст про чай. Чай бла бла бла...", ids[12]);
        Article a8 = new Article("Чай в пакетах", "Короткий текст", ids[13]);
        Article a9 = new Article("Продукт 3", "Короткий текст", ids[14]);
        Article a10 = new Article("Продукт А", "Короткий текст", ids[15]);
        Article a11 = new Article("Продукт 1", "Короткий текст", ids[16]);
        Article a12 = new Article("Продукт Я", "Короткий текст", ids[17]);
        Article a13 = new Article("Продукт Г", "Короткий текст", ids[18]);
        Article a14 = new Article("Продукт 2", "Короткий текст", ids[19]);

        assert storageProduct != null;
        storageProduct.put(ids[0], p1);
        storageProduct.put(ids[1], p2);
        storageProduct.put(ids[2], p3);
        storageProduct.put(ids[3], p4);
        storageProduct.put(ids[4], p5);
        storageProduct.put(ids[5], p6);

        assert storageArticle != null;
        storageArticle.put(ids[6], a1);
        storageArticle.put(ids[7], a2);
        storageArticle.put(ids[8], a3);
        storageArticle.put(ids[9], a4);
        storageArticle.put(ids[10], a5);
        storageArticle.put(ids[11], a6);
        storageArticle.put(ids[12], a7);
        storageArticle.put(ids[13], a8);
        storageArticle.put(ids[14], a9);
        storageArticle.put(ids[15], a10);
        storageArticle.put(ids[16], a11);
        storageArticle.put(ids[17], a12);
        storageArticle.put(ids[18], a13);
        storageArticle.put(ids[19], a14);
    }

    public Map<UUID, Product> getStorageProduct() {
        return storageProduct;
    }

    public Map<UUID, Article> getStorageArticle() {
        return storageArticle;
    }

    public List<Searchable> getSearchableCollection() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(storageProduct.values());
        result.addAll(storageArticle.values());
        return result;
    }
}
