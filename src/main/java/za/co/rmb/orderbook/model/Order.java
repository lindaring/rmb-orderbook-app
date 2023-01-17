package za.co.rmb.orderbook.model;

import za.co.rmb.orderbook.enums.Side;

import java.time.LocalTime;

public record Order(Long id, Integer price, Integer quantity, Side side, LocalTime time) implements Comparable<Order> {

  @Override
  public int compareTo(Order o) {
    // Compare price in descending order if the side=BUY, otherwise compares in ascending order (side=SELL).
    int priceComparison;
    if (side == Side.BUY) {
      priceComparison = o.price().compareTo(this.price());
    } else { // SELL
      priceComparison = this.price().compareTo(o.price());
    }
    // Don't consider time is price is not equal
    if (priceComparison != 0) {
      return priceComparison;
    }
    // Take time into consideration if price is equal
    int timeComparison;
    if (this.time().isBefore(o.time())) {
      timeComparison = -1;
    } else if (this.time().isAfter(o.time())) {
      timeComparison = 1;
    } else {
      timeComparison = 0;
    }
    return timeComparison;
  }
}
