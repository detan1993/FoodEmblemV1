/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author David
 */
@javax.ws.rs.ApplicationPath("Resources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(debugmapper.DebugExceptionMapper.class);
        resources.add(ws.CORSFilter.class);
        resources.add(ws.CallWaiterResource.class);
        resources.add(ws.CustomerReservationResource.class);
        resources.add(ws.CustomerResource.class);
        resources.add(ws.FoodEmblemResource.class);
        resources.add(ws.PromotionResource.class);
        resources.add(ws.RestaurantDishResource.class);
        resources.add(ws.RestaurantEmployeeResource.class);
        resources.add(ws.RestaurantInventoryBySupplierResource.class);
        resources.add(ws.RestaurantResource.class);
        resources.add(ws.SensorResource.class);
    }
    
}
