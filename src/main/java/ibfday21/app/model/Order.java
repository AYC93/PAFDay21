package ibfday21.app.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonValue;

public class Order {
    private int id;
    private String shipName;
    private String shippingFee;
    private Customer customer;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    
    public String getShipName() {return shipName;}
    public void setShipName(String shipName) {this.shipName = shipName;}
    
    public String getShippingFee() {return shippingFee;}
    public void setShippingFee(String shippingFee) {this.shippingFee = shippingFee;}
    
    public Customer getCustomer() {return customer;}
    public void setCustomer(Customer customer) {this.customer = customer;}

    @Override
    public String toString() {
        return "Order [id=" + id + ", shipName=" + shipName + ", shippingFee=" + shippingFee + ", customer=" + customer
                + "]";
                // to print out see values of object
    }

    public static Order create(SqlRowSet rs) {
        Order order = new Order();
        Customer customer = new Customer();

        customer.setId(rs.getInt("customer_id"));
        order.setCustomer(customer);
        order.setId(rs.getInt("order_id"));
        order.setShipName(rs.getString("ship_name"));
        order.setShippingFee(rs.getString("shipping_fee"));

        return order;
    }

    public JsonValue toJson() {

        return Json.createObjectBuilder()
                .add("customer_id", getCustomer().getId())
                .add("order_id", getId())
                .add("ship_name", getShipName())
                .add("shipping_fee", getShippingFee())
                .build();
            }
        }