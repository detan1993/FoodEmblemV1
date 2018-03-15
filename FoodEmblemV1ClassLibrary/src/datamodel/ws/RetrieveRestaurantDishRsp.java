/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.Dish;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author David
 */

@XmlRootElement
@XmlType(name = "retrieveRestaurantDishRsp", propOrder = {
    "dishes"
})

public class RetrieveRestaurantDishRsp implements Serializable{
    
      private static final long serialVersionUID = 1L;
      
      private List<Dish> dishes;

    public RetrieveRestaurantDishRsp() {
    }

    public RetrieveRestaurantDishRsp(List<Dish> dishes) {
        this.dishes = dishes;
    }
          

    /**
     * @return the dishes
     */
    public List<Dish> getDishes() {
        return dishes;
    }

    /**
     * @param dishes the dishes to set
     */
    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
      
      
    
}
