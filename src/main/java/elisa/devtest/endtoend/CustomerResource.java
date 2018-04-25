package elisa.devtest.endtoend;

import elisa.devtest.endtoend.dao.CustomerDao;
import elisa.devtest.endtoend.model.Customer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/customers")
public class CustomerResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomers() {
        return new CustomerDao().findCustomers();
    }
}
