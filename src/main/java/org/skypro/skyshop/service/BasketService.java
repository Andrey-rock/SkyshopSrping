package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        if (!storageService.getProductById(id).isPresent()) {
            throw new IllegalArgumentException("Товара с данным id не найдено");
        }
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> list = productBasket.getProducts().entrySet().stream()
                .map(p -> new BasketItem(storageService.getProductById(p.getKey()).orElse(null), p.getValue()))
                .collect(Collectors.toList());
        return new UserBasket(list);
    }
}
