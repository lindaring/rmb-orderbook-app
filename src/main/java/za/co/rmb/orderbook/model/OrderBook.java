package za.co.rmb.orderbook.model;

import java.util.Queue;

public record OrderBook(Queue<Order> buyOrders, Queue<Order> sellOrders) {
}
