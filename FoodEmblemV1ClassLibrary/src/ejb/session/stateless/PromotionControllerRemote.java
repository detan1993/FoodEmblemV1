/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Promotion;
import java.util.List;

public interface PromotionControllerRemote {

    public Promotion createPromotion(Promotion newPromotion);
    public List<Promotion> retrievePromotionsByRestaurantId(long restaurantId);
}
