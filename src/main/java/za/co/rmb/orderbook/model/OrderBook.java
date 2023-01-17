package za.co.rmb.orderbook.model;

import java.util.Queue;

public record OrderBook(Queue<Order> orders) {
}
