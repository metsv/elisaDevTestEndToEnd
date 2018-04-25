package elisa.devtest.endtoend.dao;

import elisa.devtest.endtoend.model.Customer;
import elisa.devtest.endtoend.model.Order;
import elisa.devtest.endtoend.model.OrderLine;
import elisa.devtest.endtoend.model.StoreOrder;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;

public class OrderDao extends DaoBase {

  public List<Order> findOrders() {
    try {
      return createJdbcTemplate().query("SELECT * FROM orders", (resultSet, rowNumber) -> new Order(resultSet.getLong("order_id"), findCustomer(resultSet.getLong("customer_id")), findOrderLines(resultSet.getLong("order_id"))));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Collections.EMPTY_LIST;
  }

  public Order findOrder(long orderId) {
    try {
      return createJdbcTemplate().queryForObject("SELECT * FROM orders WHERE order_id = ?", new Object[]{orderId}, (resultSet, rowNumber) -> new Order(resultSet.getLong("order_id"), findCustomer(resultSet.getLong("customer_id")), findOrderLines(resultSet.getLong("order_id"))));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private List<OrderLine> findOrderLines(long orderId) {
    try {
      return createJdbcTemplate().query("SELECT * FROM order_line WHERE order_id = ?", new Object[]{orderId}, (resultSet, rowNumber) -> new OrderLine(resultSet.getLong("order_line_id"), resultSet.getString("product_id"), resultSet.getString("product_name"), resultSet.getInt("quantity"), resultSet.getDouble("price")));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Collections.EMPTY_LIST;
  }

  public Customer findCustomer(final long customerId) {
    try {
      return createJdbcTemplate().queryForObject("SELECT * FROM customer WHERE customer_id = ?", new Object[]{customerId}, (resultSet, rowNumber) -> new Customer(resultSet.getLong("customer_id"), resultSet.getString("company_name"), resultSet.getString("street"), resultSet.getString("postal_code"), resultSet.getString("city"), resultSet.getString("country")));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new Customer();
  }

  // TODO: Add transaction support
  public Order store(StoreOrder order) {
    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
    final String insertOrderSql = "INSERT INTO orders(order_id, customer_id) VALUES(order_seq.NEXTVAL, ?)";

    createJdbcTemplate().update(con -> {
        PreparedStatement ps = con.prepareStatement(insertOrderSql, new String[]{"order_id"});
        ps.setLong(1, order.getCustomerId());
        return ps;
      }
      , keyHolder);

    long orderId = keyHolder.getKey().longValue();
    order.getOrderLines().stream().forEach(orderLine -> storeOrderLine(orderId, orderLine));
    return findOrder(orderId);
  }


  public void storeOrderLine(Long orderId, OrderLine orderLine) {
    final String insertOrderLineSql =
      "INSERT INTO order_line(order_line_id, order_id, product_id, product_name, quantity, price) VALUES(order_line_seq.NEXTVAL, ?, ?, ?, ?, ?)";

    createJdbcTemplate().update(insertOrderLineSql, orderId, orderLine.getProductId(), orderLine.getProductName(), orderLine.getQuantity(), orderLine.getPrice());
  }
}
