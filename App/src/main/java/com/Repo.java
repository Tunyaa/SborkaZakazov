package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Repo {

    Statement statement;
    HashMap<Integer, String> categoriesMap;

    private void getStatement() {
        try {
            Class.forName("org.postgresql.Driver");

            // Устанавливаем параметры подключения к базе данных
            String url = "jdbc:postgresql://localhost:5432/master";
            Properties autorization = new Properties();

            autorization.put("user", "vesper");
            autorization.put("password", "1991ms");

            // Устанавливаем соединение с базой данных
            Connection connection = DriverManager.getConnection(url, autorization);

            // Создаем объект Statement для выполнения SQL-запросов
            this.statement = connection.createStatement();

            // Выполняем SQL-запрос SELECT для получения данных из таблицы t_order_composition            
            String query = "SELECT * FROM t_order_composition";
            ResultSet ordersComposition = statement.executeQuery(query);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            
        }
    }

    public List getOrders(int[] args) {

        List<OrderComposition> findAll = new LinkedList<>();
        try {

            // Выполняем SQL-запрос SELECT для получения данных из таблицы t_categories  
            getStatement();
            String query = "SELECT * FROM t_categories";
            ResultSet t_categories = statement.executeQuery(query);

            // Заполняем map именами категорий
            categoriesMap = new HashMap<>();
            while (t_categories.next()) {
                categoriesMap.put(t_categories.getInt("id"), t_categories.getString("name"));
            }

            // Выполняем SQL-запрос SELECT для получения данных из таблицы t_order_composition 
            for (int i = 0; i < args.length; i++) {
                getStatement();
                query = "SELECT * FROM t_order_composition where order_id = " + args[i];
                ResultSet ordersComposition = statement.executeQuery(query);

                // Обрабатываем результаты запроса
                while (ordersComposition.next()) {
                    int id = ordersComposition.getInt("id");
                    int orderId = ordersComposition.getInt("order_id");
                    int itemId = ordersComposition.getInt("item_id");
                    int quantity = ordersComposition.getInt("quantity");
                    String category = categoriesMap.get(ordersComposition.getInt("item_id"));
                    findAll.add(new OrderComposition(id, orderId, itemId, category, quantity));
                }
            }

            return findAll;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
