package elisa.devtest.endtoend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderLine {
  private long orderLineId;
  private String productId;
  private String productName;
  private int quantity;
  private Double price;


  public OrderLine(long orderLineId, String productId, String productName, int quantity, Double price) {
    this.orderLineId = orderLineId;
    this.productId = productId;
    this.productName = productName;
    this.quantity = quantity;
    this.price = price;
  }

  public OrderLine() {
  }

  public long getOrderLineId() {
    return orderLineId;
  }

  public String getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public int getQuantity() {
    return quantity;
  }

  public Double getPrice() {
    return price;
  }
}
