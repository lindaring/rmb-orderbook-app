package za.co.rmb.orderbook.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.rmb.orderbook.model.Order;
import za.co.rmb.orderbook.model.OrderBook;

import java.util.PriorityQueue;

@SpringBootTest
public class OrderBookServiceTest {

  @Autowired
  private OrderBookService orderBookService;

  @BeforeEach
  public void checkForNull() {
    OrderBook orderBook = orderBookService.getOrderBook();
    Assertions.assertNotNull(orderBook);
  }

  /**
   * In the buy order queue the price at the beginning of the queue should be the highest.
   */
  @Test
  public void BUY_ORDER_MAX_QUEUE_TEST() {
    // Creating a copy to avoid breaking other tests
    PriorityQueue<Order> copyOfBuyOrders = new PriorityQueue<>(orderBookService.getOrderBook().buyOrders());
    Order firstInQueue = copyOfBuyOrders.peek();
    Assertions.assertNotNull(firstInQueue);

    int maxPrice = firstInQueue.price();
    boolean higherPriceFound = copyOfBuyOrders.stream().anyMatch(order -> order.price() > maxPrice);
    Assertions.assertFalse(higherPriceFound);
  }

  /**
   * In the sell order queue the price at the beginning of the queue should be the lowest.
   */
  @Test
  public void SELL_ORDER_MIN_QUEUE_TEST() {
    // Creating a copy to avoid breaking other tests
    PriorityQueue<Order> copyOfSellOrders = new PriorityQueue<>(orderBookService.getOrderBook().sellOrders());
    Order firstInQueue = copyOfSellOrders.peek();
    Assertions.assertNotNull(firstInQueue);

    int minPrice = firstInQueue.price();
    boolean lowerPriceFound = copyOfSellOrders.stream().anyMatch(order -> order.price() < minPrice);
    Assertions.assertFalse(lowerPriceFound);
  }

  @Test
  public void testBuyOrderBookInit() {
    OrderBook orderBook = orderBookService.getOrderBook();
    Assertions.assertNotNull(orderBook);
    orderBook.buyOrders().forEach(System.out::println);
    System.out.println();

    for (int i = 0; i < 3; i++) {
      orderBook.buyOrders().poll();
      orderBook.buyOrders().forEach(System.out::println);
      System.out.println();
    }
  }

  @Test
  public void testSellOrderBookInit() {
    OrderBook orderBook = orderBookService.getOrderBook();
    Assertions.assertNotNull(orderBook);
    orderBook.sellOrders().forEach(System.out::println);
    System.out.println();

    for (int i = 0; i < 3; i++) {
      orderBook.sellOrders().poll();
      orderBook.sellOrders().forEach(System.out::println);
      System.out.println();
    }
  }
}
