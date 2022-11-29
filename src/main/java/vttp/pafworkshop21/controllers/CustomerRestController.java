package vttp.pafworkshop21.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import vttp.pafworkshop21.models.Customer;
import vttp.pafworkshop21.repositories.CustomerRepo;
import vttp.pafworkshop21.services.CustomerService;

@RestController
@RequestMapping ("/api/customers")
public class CustomerRestController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRestController.class);

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CustomerService customerService;
    
    @GetMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCustomerList(
        @RequestParam (required = false) String limit, @RequestParam(required = false) String offset ){
            if (limit == null) limit = "5";
            if (offset == null) offset = "0";
            List<String> customerList = customerRepo.getCustomerList(limit, offset);
            JsonArray jsonArray = Json.createArrayBuilder(customerList).build();
            return ResponseEntity.ok(jsonArray.toString());
        }
    

    @GetMapping (path = "{customer_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCustomerById(@PathVariable String customer_id){
        JsonObject o = null;
        try{
            Customer customer = customerRepo.getCustomerById(customer_id);
            o = customer.toJson();


        } catch (IndexOutOfBoundsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e +" \n" + customer_id + " not found");
        }
        return ResponseEntity.ok(o.toString());
    }


    @GetMapping(path="{customer_id}/orders" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCustomerOrder(@PathVariable String customer_id){
        JsonArray arr = null;
        try{
            customerRepo.getCustomerById(customer_id);
            List<String> orderList = customerRepo.getOrderList(customer_id);
            arr = Json.createArrayBuilder(orderList).build();

        } catch (IndexOutOfBoundsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e +" \n" + customer_id + " not found");
        }
        return ResponseEntity.ok(arr.toString());
    }
}

    

