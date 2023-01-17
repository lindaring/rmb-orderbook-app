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
    Queue<Order> orders = new PriorityQueue<>();

    orders.add(new Order(101L, 33, 10, Side.BUY, LocalTime.of(12, 3,18)));
    orders.add(new Order(102L, 33, 5, Side.BUY, LocalTime.of(11, 30,1)));
    orders.add(new Order(103L, 34, 7, Side.BUY, LocalTime.of(12, 0,22)));
    orders.add(new Order(104L, 35, 12, Side.BUY, LocalTime.of(13, 50,45)));
    orders.add(new Order(105L, 34, 14, Side.BUY, LocalTime.of(13, 24,23)));

    this.orderBook = new OrderBook(orders);
  }

  public OrderBook getOrderBook() {
    return orderBook;
  }

}
