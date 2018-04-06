/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Promotion;
import java.util.List;


public interface PromotionControllerLocal {
      public Promotion createPromotion(Promotion newPromotion);
      public Boolean deletePromotion(long promotionId);
      public Promotion updatePromotion (Promotion oldPromotion);
      public Promotion createRestaurantPromotion(Promotion newPromotion, long restaurantid);
    public List<Promotion> retrievePromotionsByRestaurantId(long restaurantId);
    public Promotion retrieveRestaurantPromoFromBeacon(String sensorId);
}
