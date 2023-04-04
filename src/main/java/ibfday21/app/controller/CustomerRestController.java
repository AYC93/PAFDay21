package ibfday21.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibfday21.app.model.Customer;
import ibfday21.app.model.Order;
import ibfday21.app.repository.CustomerRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@RestController
@RequestMapping(path = "/api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerRestController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping()
    public ResponseEntity<String> getAllCustomers(@RequestParam(required = false) String limit, 
    @RequestParam(required = false) String offset){

        if(Objects.isNull(limit)) 
            limit = "5";
        if(Objects.isNull(offset)) 
            offset = "0";

        List<Customer> customers = customerRepository.getAllCustomer
                    (Integer.parseInt(offset), Integer.parseInt(limit));

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for(Customer c: customers){
            arrayBuilder.add(c.toJson());
        }

        JsonArray result = arrayBuilder.build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());
    }
    
    // fetch customer using customer id
    
    @GetMapping(path="{customerId}")
    public ResponseEntity<String> getCustomerById(@PathVariable Integer customerId){

        JsonObject result = null;
        
        try{
        Customer customer = customerRepository.findCustomerById(customerId);
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("customer", customer.toJson());

        result = objectBuilder.build();

        }catch(IndexOutOfBoundsException e){            
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"error_msg\":\"record not found\"}");
        }

        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toString());
    }

    @GetMapping(path="{customerId}/orders")
    public ResponseEntity<String> getOrdersForCustomer(@PathVariable Integer customerId){

        List<Order> orders = new ArrayList<>();

        orders = customerRepository.getCustomerOrders(customerId);

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for(Order o: orders){
            arrayBuilder.add(o.toJson());
        }

        JsonArray result = arrayBuilder.build();

        if(result.size() == 0) // might need to do this if try catch doesn't work
            return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body("{\"error_msg\":\"record not found\"}");

        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toString());
    }

}
