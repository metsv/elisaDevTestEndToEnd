package elisa.devtest.endtoend.dao;


import elisa.devtest.endtoend.DbBootstrap;
import elisa.devtest.endtoend.model.Order;
import elisa.devtest.endtoend.model.OrderLine;
import elisa.devtest.endtoend.model.StoreOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderDaoTest {

  @BeforeAll
  public static void setup() {
    new DbBootstrap().bootstratp();
  }

  @Test
  public void store() {
    Order order = new OrderDao().store(new StoreOrder(1l));
    assertEquals(1L, order.getCustomer().getCustomerId());
    assertEquals("Putka Oy", order.getCustomer().getCompanyName());
    assertTrue(order.getOrderId() > 0);
  }

  @Test
  public void storeOrderLine() {
    OrderDao orderDao = new OrderDao();

    Order storedOrder = orderDao.store(new StoreOrder(1l));
    long orderId = storedOrder.getOrderId();

    orderDao.storeOrderLine(orderId, new OrderLine(0, "1", "some-product-name", 2, new Double(10.12)));

    Order result = orderDao.findOrder(orderId);

    assertEquals(orderId, result.getOrderId());
    assertEquals(1L, result.getCustomer().getCustomerId());
    assertEquals("Putka Oy", result.getCustomer().getCompanyName());

    assertEquals(1, result.getOrderLines().size());
    OrderLine orderLine1 = result.getOrderLines().get(0);
    assertEquals("1", orderLine1.getProductId());
    assertEquals("some-product-name", orderLine1.getProductName());
    assertEquals(2, orderLine1.getQuantity());
    assertEquals(new Double(10.12), orderLine1.getPrice());
  }
}
