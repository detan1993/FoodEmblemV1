/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Fridge;
import java.util.List;

public interface FridgeControllerLocal {

    public Fridge createFridge(Fridge newFridge);
      public List<Fridge> retrieveFridgesByRestaurantId(long restaurantId);
      public void updateTempTest(long restaurantId);
    
}
