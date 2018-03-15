/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Dish;
import java.util.List;


public interface RestaurantDishControllerLocal {

    public Dish createDish(Dish newDish);

    public List<Dish> getDishes(Long restaurantId);
    
}
