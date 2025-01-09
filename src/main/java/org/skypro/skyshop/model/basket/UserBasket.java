package org.skypro.skyshop.model.basket;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBasket that = (UserBasket) o;
        return Objects.equals(items, that.items) && Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, total);
    }
}
