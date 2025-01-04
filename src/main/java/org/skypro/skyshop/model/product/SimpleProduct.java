package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    protected int price;

    public SimpleProduct(String name, int price, UUID id) {
        super(name, id);
        if (price > 0) {
            this.price = price;
        }
        else {
            throw new IllegalArgumentException("Цена должна быть больше нуля");
        }
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice();
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}

