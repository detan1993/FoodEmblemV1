/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.RestaurantSeating;
import java.util.List;

public interface RestaurantSeatingControllerRemote {

    public RestaurantSeating createNewSeating(RestaurantSeating newSeat);

    public List<RestaurantSeating> retrieveSeatsByRestaurantId(long restaurantId);
    
}
