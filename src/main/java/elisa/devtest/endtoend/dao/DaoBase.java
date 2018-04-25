package elisa.devtest.endtoend.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class DaoBase {
  JdbcTemplate createJdbcTemplate() {
    return new JdbcTemplate(DBConnection.getDataSource());
  }
}
