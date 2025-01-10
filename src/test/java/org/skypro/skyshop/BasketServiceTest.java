package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    //Добавление несуществующего товара
    @Test
    void whenAddNoSuitableProduct() {
        Mockito.when(storageService.getProductById(any(UUID.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchProductException.class, () -> basketService.addProduct(UUID.randomUUID()));
    }

    //Добавление существующего товара
    @Test
    void whenAddSuitableProduct() {
        UUID id = UUID.randomUUID();
        Product p1 = new FixPriceProduct("Масло", id);
        Mockito.when(storageService.getProductById(any(UUID.class))).thenReturn(Optional.of(p1));
        basketService.addProduct(id);
        Mockito.verify(productBasket).addProduct(id);
    }

    //Возврат пустой корзины, если ProductBasket пуст
    @Test
    void whenBasketEmpty() {
        Mockito.when(productBasket.getProducts()).thenReturn(Collections.emptyMap());
        Assertions.assertEquals(new UserBasket(Collections.emptyList()), basketService.getUserBasket());
    }

    //Возврат корзины, если ProductBasket содержит товары
    @Test
    void whenBasketContainsProducts() {
        UUID id = UUID.randomUUID();
        Product p1 = new SimpleProduct("Капуста", 30, id);
        BasketItem item = new BasketItem(p1, 1);
        List<BasketItem> basketItems = new ArrayList<>();
        basketItems.add(item);

        Mockito.when(productBasket.getProducts()).thenReturn(Map.of(id, 1));
        Mockito.when(storageService.getProductById(any(UUID.class))).thenReturn(Optional.of(p1));

        Assertions.assertEquals(new UserBasket(basketItems), basketService.getUserBasket());
    }
}
