package za.co.rmb.orderbook.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.rmb.orderbook.model.Order;
import za.co.rmb.orderbook.model.OrderBook;

import java.util.*;

@SpringBootTest
public class OrderBookServiceTest {

  @Autowired
  private OrderBookService orderBookService;

  @BeforeEach
  public void checkForNull() {
    OrderBook orderBook = orderBookService.getOrderBook();
    Assertions.assertNotNull(orderBook);
    orderBookService.init();
    Assertions.assertNotNull(orderBook.buyOrdersMap());
    Assertions.assertNotNull(orderBook.sellOrdersMap());
  }

  @Test
  public void BUY_ORDER_MAP_IN_DESCENDING_ORDER_TEST() {
    Map<Integer, Set<Order>> buyOrdersMap = orderBookService.getOrderBook().buyOrdersMap();
    Assertions.assertNotNull(buyOrdersMap);

    Integer previousKey = null;
    for (int key: buyOrdersMap.keySet()) {
      if (previousKey != null) {
        Assertions.assertTrue(key < previousKey);
      }
      previousKey = key;
    }
  }

  @Test
  public void SELL_ORDER_MAP_IN_ASCENDING_ORDER_TEST() {
    Map<Integer, Set<Order>> sellOrdersMap = orderBookService.getOrderBook().sellOrdersMap();
    Assertions.assertNotNull(sellOrdersMap);

    Integer previousKey = null;
    for (int key: sellOrdersMap.keySet()) {
      if (previousKey != null) {
        Assertions.assertTrue(key > previousKey);
      }
      previousKey = key;
    }
  }

  @Test
  public void BUY_ORDER_MAP_IN_DESCENDING_ORDER_AFTER_FIRST_ENTRY_REMOVED_TEST() {
    Map<Integer, Set<Order>> buyOrdersMap = orderBookService.getOrderBook().buyOrdersMap();

    Integer firstKey = buyOrdersMap.keySet().stream().findFirst().orElse(null);
    Assertions.assertNotNull(firstKey);

    buyOrdersMap.remove(firstKey);

    Integer previousKey = null;
    for (int key: buyOrdersMap.keySet()) {
      if (previousKey != null) {
        Assertions.assertTrue(key < previousKey);
      }
      previousKey = key;
    }
  }

  @Test
  public void SELL_ORDER_MAP_IN_ASCENDING_ORDER_AFTER_FIRST_ENTRY_REMOVED_TEST() {
    Map<Integer, Set<Order>> sellOrdersMap = orderBookService.getOrderBook().sellOrdersMap();

    Integer firstKey = sellOrdersMap.keySet().stream().findFirst().orElse(null);
    Assertions.assertNotNull(firstKey);

    sellOrdersMap.remove(firstKey);

    Integer previousKey = null;
    for (int key: sellOrdersMap.keySet()) {
      if (previousKey != null) {
        Assertions.assertTrue(key < previousKey);
      }
      previousKey = key;
    }
  }
}
