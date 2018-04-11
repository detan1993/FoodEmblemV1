/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CallWaiter;
import java.util.List;

/**
 *
 * @author JiongYi
 */

public interface CallWaiterControllerLocal {
    public boolean requestWaiter(String table);
    public List<CallWaiter>GetRestaurantWaiterRequests();
    public boolean clearRequest(long id);
}
