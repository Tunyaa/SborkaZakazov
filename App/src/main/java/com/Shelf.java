package com;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Shelf {

    private HashMap<String, HashSet<OrderComposition>> shelf;

    public Shelf() {
        shelf = new HashMap<>();
    }

    public void putOnShelf(List<OrderComposition> orders) {

        // Создаём стелажи
        shelf.put("A", new HashSet<>());
        shelf.put("Б", new HashSet<>());
        shelf.put("Ж", new HashSet<>());

        // Распределяем товары по слелажам в соотвестствии с id товара
        for (OrderComposition orderComposition : orders) {
            if (orderComposition.getItemId() == 1 | orderComposition.getItemId() == 2) {
                shelf.get("A").add(orderComposition);
            }

            if (orderComposition.getItemId() == 3) {
                shelf.get("Б").add(orderComposition);
            }

            if (orderComposition.getItemId() >= 4 & orderComposition.getItemId() <= 6) {
                shelf.get("Ж").add(orderComposition);
            }
        }

    }

    public void printShelf() {

        Set<String> keySet = shelf.keySet();

        for (String string : keySet) {
            if (!shelf.get(string).isEmpty()) {
                Iterator<OrderComposition> iterator = shelf.get(string).iterator();
            
            System.out.println("Стеллаж " + string);
            while (iterator.hasNext()) {
                System.out.println(iterator.next().toString() + "\n");

            }
            System.out.println("");
            }
            

        }

    }

}
