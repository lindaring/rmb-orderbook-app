package za.co.rmb.orderbook.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.rmb.orderbook.model.OrderBook;

@SpringBootTest
public class OrderBookServiceTest {

  @Autowired
  private OrderBookService orderBookService;

  @Test
  public void testOrderBookInit() {
    OrderBook orderBook = orderBookService.getOrderBook();
    Assertions.assertNotNull(orderBook);
    orderBook.orders().forEach(System.out::println);
    System.out.println();

    for (int i = 0; i < 3; i++) {
      orderBook.orders().poll();
      orderBook.orders().forEach(System.out::println);
      System.out.println();
    }
  }
}
