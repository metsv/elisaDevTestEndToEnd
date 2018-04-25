package elisa.devtest.endtoend;

import elisa.devtest.endtoend.dao.OrderDao;
import elisa.devtest.endtoend.model.Order;
import elisa.devtest.endtoend.model.StoreOrder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/orders")
public class OrderResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Order> getOrders() {
        return new OrderDao().findOrders();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Order storeOrder(StoreOrder order) {
        if (order == null) throw  new RuntimeException("Customer Id mandatory");
        if (order.getCustomerId() == null) throw  new RuntimeException("Customer Id mandatory");
        return new OrderDao().store(order);
    }
}
