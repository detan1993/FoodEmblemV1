/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.RestaurantEmployee;

public interface RestaurantEmployeeControllerRemote {

    public RestaurantEmployee createEmployee(RestaurantEmployee newEmployee);
      public RestaurantEmployee login(String email, String password);

    public RestaurantEmployee getProfile(String email);
    
}
