package com;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderComposition {

    private int id;

    private int orderId;

    private int itemId;

    private String category;

    private int quantity;

    @Override
    public String toString() {
        return category + " (id=" + itemId + ")" + "\nid заказа=" + orderId + ", " + quantity + " шт.";
    }

}
