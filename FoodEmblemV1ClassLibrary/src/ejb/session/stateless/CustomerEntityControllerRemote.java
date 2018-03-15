/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Customer;
import java.util.List;


public interface CustomerEntityControllerRemote {
    
     public Customer createNewCustomer(Customer newCustomer);
     public List<Customer> retrieveCustomer();
         public Customer login(String email, String password);
}
