package com;

import java.util.List;

public class App {

    private static int[] ids;

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
        read(args);

        List orders = new Repo().getOrders(ids);

        Shelf shelf = new Shelf();
        shelf.putOnShelf(orders);
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
