package vttp.pafworkshop21.repositories;

public class Queries {
    public static final String SQL_GET_CUSTOMER_LIST = "select concat(first_name,' ' ,last_name) as full_name from customers limit ? offset ?";
    public static final String SQL_GET_CUSTOMER_BY_ID = "select id , first_name, last_name, address from customers where id = ?";
    public static final String SQL_GET_CUSTOMER_ORDER_BY_ID = "select customers.id customer_id, orders.id order_id from customers join orders on customers.id = orders.customer_id where customer_id = ?";
}
