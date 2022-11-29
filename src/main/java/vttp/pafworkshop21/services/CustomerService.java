package vttp.pafworkshop21.services;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import vttp.pafworkshop21.models.Customer;

@Service
public class CustomerService {
    
    public Customer createCustomer(SqlRowSet rs){
        rs.next();
        Customer customer = new Customer();
        // will not be able to set Id if rs.getString is null
        customer.setId(rs.getString("id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setAddress(rs.getString("address"));
        return customer;
    }
}
