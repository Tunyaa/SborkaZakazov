package com;

import java.sql.SQLException;
import java.util.List;

public class App {

    private static int[] ids;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        // Аргументы строки переводим в int
        read(args);
        // Обрабатываем запрос
        Repo repo = new Repo();
        List orders = repo.getOrders(ids);
        // Распределяем товары по стеллажам
        Shelf shelf = new Shelf();
        shelf.putOnShelf(orders);
        // Вывод
        shelf.printShelf();

    }

    private static void read(String[] args) {
        try {
            App.ids = new int[args.length];
            for (int i = 0; i < args.length; i++) {
                App.ids[i] = Integer.parseInt(args[i]);
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

    }

}
