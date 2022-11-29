package vttp.pafworkshop21.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.pafworkshop21.models.Customer;
import vttp.pafworkshop21.services.CustomerService;

import static vttp.pafworkshop21.repositories.Queries.*;

import java.util.LinkedList;
import java.util.List;

@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate template;

    @Autowired
    CustomerService customerService;

    
    public List<String> getCustomerList(String limit, String offset){
        List<String> customerList = new LinkedList<>();
        System.out.println(limit);
        System.out.println(offset);
        SqlRowSet rs = template.queryForRowSet(SQL_GET_CUSTOMER_LIST, Integer.parseInt(limit), Integer.parseInt(offset));
        while (rs.next()){
            customerList.add(rs.getString("full_name"));
        }
        return customerList;
    }


    public Customer getCustomerById(String id){
        SqlRowSet rs = template.queryForRowSet(SQL_GET_CUSTOMER_BY_ID, id);
        Customer customer = customerService.createCustomer(rs);
        return customer;
    }


    public List<String> getOrderList(String id){
        List<String> orderList = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GET_CUSTOMER_ORDER_BY_ID, id);
        while (rs.next()){
            orderList.add(rs.getString("order_id"));
        }
        return orderList;
    }
}
