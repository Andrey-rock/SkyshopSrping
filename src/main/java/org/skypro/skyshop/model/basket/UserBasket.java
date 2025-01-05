package org.skypro.skyshop.model.basket;

import java.util.List;

public final class UserBasket {
    private final List<BasketItem> items;
    private final Double total;

    public UserBasket(List<BasketItem> items) {
        this.items = items;
        this.total = items.stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();
    }

    public List<BasketItem> getItems() {
        return items;
    }
    public Double getTotal() {
        return total;
    }
}
