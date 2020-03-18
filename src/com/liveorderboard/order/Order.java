package com.liveorderboard.order;

public class Order {
    public String userId;
    public Double orderQuantity;
    public Double price;
    public OrderType type;

    public enum OrderType { BUY, SELL;}

    @Override
    public String toString() {
        return "{\"_class\":\"Order\", " +
                "\"userId\":" + (userId == null ? "null" : "\"" + userId + "\"") + ", " +
                "\"orderQuantity\":" + (orderQuantity == null ? "null" : "\"" + orderQuantity + "\"") + ", " +
                "\"price\":" + (price == null ? "null" : "\"" + price + "\"") + ", " +
                "\"type\":" + (type == null ? "null" : type) +
                "}";
    }

    public Order() {   }

    public Order(String userId, Double orderQuantity, Double price, OrderType type) {
        this.userId = userId;
        this.orderQuantity = orderQuantity;
        this.price = price;
        this.type = type;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Double getOrderQuantity() { return orderQuantity; }
    public void setOrderQuantity(Double orderQuantity) { this.orderQuantity = orderQuantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public OrderType getType() { return type; }
    public void setType(OrderType type) { this.type = type; }
}
