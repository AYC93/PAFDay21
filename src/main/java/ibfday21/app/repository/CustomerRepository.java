package ibfday21.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibfday21.app.model.Customer;
import ibfday21.app.model.Order;

import static ibfday21.app.repository.DBQueries.*; // if static not here, have to write DBQueries.SELECT_ALL_CUSTOMERS at queryForRowSet instead

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomer(Integer offset, Integer limit) {

        List<Customer> csts = new ArrayList<>();

        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_ALL_CUSTOMERS, offset, limit);

        while (rs.next()){
            csts.add(Customer.create(rs));
        }

        return csts;
    }

    public Customer findCustomerById(Integer id){
        
        List<Customer> customers = jdbcTemplate.query(SELECT_CUSTOMER_BY_ID, new CustomerRowMapper(), new Object[] {id});

        return customers.get(0);
    }

    public List<Order> getCustomerOrders(Integer id){
    
        List<Order> orders = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_ORDER_BY_CUSTOMER_ID, new Object[] {id});

        while(rs.next()){
            orders.add(Order.create(rs));
        }
        return orders;
    }

}
