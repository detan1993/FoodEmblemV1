/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Customer;
import entity.OrderDish;
import entity.Reservation;
import entity.RestaurantCustomerOrder;
import java.util.List;


public interface CustomerEntityControllerLocal {

    public Customer createNewCustomer(Customer newCustomer);
    public List<Customer> retrieveCustomer();

    public Customer login(String email, String password);

    public boolean addNewReservationRecord(Reservation newReservation, String customerId);
    public RestaurantCustomerOrder addCustomerOrder(String email, List<OrderDish>orderDishes, Double total);
}
