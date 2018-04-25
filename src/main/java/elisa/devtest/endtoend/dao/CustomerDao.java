package elisa.devtest.endtoend.dao;

import elisa.devtest.endtoend.model.Customer;

import java.util.Collections;
import java.util.List;

public class CustomerDao extends DaoBase {
  public List<Customer> findCustomers() {
    try {
      return createJdbcTemplate().query("SELECT * FROM customer", (resultSet, rowNumber) -> new Customer(resultSet.getLong("customer_id"), resultSet.getString("company_name"), resultSet.getString("street"), resultSet.getString("postal_code"), resultSet.getString("city"), resultSet.getString("country")));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Collections.EMPTY_LIST;
  }
}
