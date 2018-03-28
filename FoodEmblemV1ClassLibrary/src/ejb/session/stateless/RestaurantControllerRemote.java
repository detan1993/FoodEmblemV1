/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Restaurant;
import java.util.List;

public interface RestaurantControllerRemote {
     public Restaurant createRestaurant(Restaurant newRestaurant);
   public List<Restaurant> retrieveRestaurant();    
     public List<Restaurant> retrievePartnerRestaurant(List<String> restApiKeys);
  
   
}
