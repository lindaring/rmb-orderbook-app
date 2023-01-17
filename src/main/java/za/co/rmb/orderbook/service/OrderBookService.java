package za.co.rmb.orderbook.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import za.co.rmb.orderbook.enums.Side;
import za.co.rmb.orderbook.model.Order;
import za.co.rmb.orderbook.model.OrderBook;

import java.time.LocalTime;
import java.util.PriorityQueue;
import java.util.Queue;

@Service
public class OrderBookService {

  private OrderBook orderBook = null;

  @PostConstruct
  public void init() {
    /*
     * NOTE:
     * 1. Using a queue data structure because the limit order book has the priority: FIFO.
     *    The order at the top will be processed first, and removed from the queue.
     * 2. Using PriorityQueue (heap implementation) because it provides O(log n) time complexity
     *    for the enqueuing and dequeuing methods (offer, poll, remove() and add);
     *    linear time for the remove(Object) and contains(Object) methods;
     *    and constant time for the retrieval methods (peek, element, and size).
     */
    Queue<Order> buyOrders = new PriorityQueue<>();
    buyOrders.add(new Order(101L, 33, 10, Side.BUY, LocalTime.of(12, 3,18)));
    buyOrders.add(new Order(102L, 33, 5, Side.BUY, LocalTime.of(11, 30,1)));
    buyOrders.add(new Order(103L, 34, 7, Side.BUY, LocalTime.of(12, 0,22)));
    buyOrders.add(new Order(104L, 35, 12, Side.BUY, LocalTime.of(13, 50,45)));
    buyOrders.add(new Order(105L, 34, 14, Side.BUY, LocalTime.of(13, 24,23)));

    Queue<Order> sellOrders = new PriorityQueue<>();
    sellOrders.add(new Order(106L, 30, 6, Side.SELL, LocalTime.of(9, 31,59)));
    sellOrders.add(new Order(107L, 35, 9, Side.SELL, LocalTime.of(10, 14,16)));
    sellOrders.add(new Order(108L, 35, 11, Side.SELL, LocalTime.of(12, 44,36)));
    sellOrders.add(new Order(109L, 29, 8, Side.SELL, LocalTime.of(14, 16,46)));
    sellOrders.add(new Order(110L, 30, 10, Side.SELL, LocalTime.of(10, 33,21)));

    this.orderBook = new OrderBook(buyOrders, sellOrders);
  }

  public OrderBook getOrderBook() {
    return orderBook;
  }

}
