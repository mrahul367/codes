package com.liveorderboard.order;

import java.util.*;

import static com.liveorderboard.order.Order.OrderType.BUY;
import static com.liveorderboard.order.Order.OrderType.SELL;
import static java.text.NumberFormat.getCurrencyInstance;

public class LiveOrderBoard {

    public static final Map<String, List<Order>> orderBoard = new HashMap<String, List<Order>>();
    public static final Map<Integer, Double> quantityBuyingAtPrice = new HashMap<Integer, Double>();
    public static final Map<Integer, Double> quantitySellingAtPrice = new HashMap<Integer, Double>();

    public static void main(String[] args) {

        registerOrder(new Order("A", 2.0, 200.0, BUY));
        registerOrder(new Order("B", 1.0, 231.0, BUY));
        registerOrder(new Order("C", 1.0, 321.0, BUY));
        registerOrder(new Order("A", 3.0, 433.0, BUY));
        registerOrder(new Order("A", 3.0, 200.0, BUY));

        registerOrder(new Order("W", 4.0, 132.0, SELL));
        registerOrder(new Order("X", 1.0, 20.0, SELL));
        registerOrder(new Order("C", 1.0, 132.0, SELL));
        registerOrder(new Order("Z", 1.0, 232.0, SELL));

        summarize();
        System.out.println(orderBoard.keySet());
        orderBoard.values().stream().forEach(i-> System.out.println(i.toString()));
    }

    public static void summarize() {
        System.out.println("Buying Orders: ");
        summarizeOrderType(BUY);
        System.out.println("Selling Orders: ");
        summarizeOrderType(SELL);
    }

    public static void registerOrder(Order order) {
        String userId = order.getUserId();

        if(!orderBoard.containsKey(userId)){
            addNewCustomer(userId);
        }

        List<Order> customerOrders = orderBoard.get(userId);
        if(!customerOrders.contains(order)) {
            customerOrders.add(order);
            updateQuantityAtPrice(order, tranlsateOrderTypeToMap(order.getType()));
        }
    }

    private static Map<Integer, Double> tranlsateOrderTypeToMap(Order.OrderType type) {
        return type.equals(BUY) ? quantityBuyingAtPrice : quantitySellingAtPrice;
    }

    private static void updateQuantityAtPrice(Order order, Map<Integer, Double> quantityAtPrice) {
        double totalQuantityAtPrice;

        if(quantityAtPrice.containsKey(order.getPrice().intValue())){
            totalQuantityAtPrice = quantityAtPrice.get(order.getPrice().intValue()) + order.getOrderQuantity();
        } else {
            totalQuantityAtPrice = order.getOrderQuantity();
        }

        quantityAtPrice.put(order.getPrice().intValue(), totalQuantityAtPrice);
    }

    private void removeOrder(Order order) {
        List<Order> customerOrders = orderBoard.get(order.getUserId());
        customerOrders.remove(order);
    }

    private static void addNewCustomer(String userId) {
        orderBoard.put(userId, new ArrayList<Order>());
    }

    private static void summarizeOrderType(Order.OrderType type) {

        Map<Integer, Double> quantityAtPrice = tranlsateOrderTypeToMap(type);

        for (Integer price : quantityAtPrice.keySet()) {
            System.out.println("- " + quantityAtPrice.get(price) + " kg for " + "\u00a3" + price);
        }
    }
}
