/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import ejb.session.stateless.ContainerControllerLocal;
import ejb.session.stateless.CustomerEntityControllerLocal;
import ejb.session.stateless.FridgeControllerLocal;
import ejb.session.stateless.InventoryControllerLocal;
import ejb.session.stateless.RestaurantControllerLocal;
import ejb.session.stateless.RestaurantDishControllerLocal;
import ejb.session.stateless.RestaurantEmployeeControllerLocal;
import ejb.session.stateless.RestaurantSeatingControllerLocal;
import ejb.session.stateless.SensorControllerLocal;
import entity.Container;
import entity.Customer;
import entity.Dish;
import entity.Fridge;
import entity.Inventory;
import entity.Restaurant;
import entity.RestaurantEmployee;
import entity.RestaurantSeating;
import entity.Sensor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author David
 */

@Startup
@Singleton
@LocalBean
public class DataInitialization {

    @EJB(name = "InventoryControllerLocal")
    private InventoryControllerLocal inventoryControllerLocal;

    @EJB(name = "ContainerControllerLocal")
    private ContainerControllerLocal containerControllerLocal;

    @EJB(name = "FridgeControllerLocal")
    private FridgeControllerLocal fridgeControllerLocal;

    @EJB(name = "RestaurantSeatingControllerLocal")
    private RestaurantSeatingControllerLocal restaurantSeatingControllerLocal;

    @EJB(name = "RestaurantEmployeeControllerLocal")
    private RestaurantEmployeeControllerLocal restaurantEmployeeControllerLocal;

    @EJB(name = "RestaurantDishControllerLocal")
    private RestaurantDishControllerLocal restaurantDishControllerLocal;

    @EJB(name = "SensorControllerLocal")
    private SensorControllerLocal sensorControllerLocal;

    @EJB(name = "RestaurantControllerLocal")
    private RestaurantControllerLocal restaurantControllerLocal;

    @EJB(name = "CustomerEntityControllerLocal")
    private CustomerEntityControllerLocal customerEntityControllerLocal;
    
    

    @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;

    
    
    
    public void persist(Object object) {
        em.persist(object);
    }
    
    
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public DataInitialization() {
        
      
    }
    
    @PostConstruct
    public void postConstruct(){
        
       try{
           
           System.out.println("post construct");
           if( restaurantControllerLocal.retrieveRestaurant().isEmpty())
           {
              
               initializeData();   
           }
        
      }
      catch(Exception ex)
      {
           
      }
    
    }
    
    // public Restaurant(String name, String contactNo, String address, String postalCode, String email, String apiKey, List<Sensor> sensors, List<Dish> dishes, List<Promotion> promotions, List<RestaurantEmployee> restEmployee) {
   /* public RestaurantEmployee(String firstName, String lastName, char gender, String email, String password, String restaurantRole, Restaurant restaurant) {
        this.firstName = firstName;*/

                      
    private void initializeData()
    {
     
      System.out.println("Initializing Data");
      Customer newCustomer1 = new Customer("Shan Wei" , "Shan@gmail.com" , 'M' , "91086210" ,"12345678");
      Customer newCustomer2 = new Customer("Afi" , "afi@gmail.com" , 'M' , "93509849" , "12345678");
      Customer newCustomer3 = new Customer("Jiong Yi" , "jy@gmail.com" , 'M', "91268920" , "12345678");
      customerEntityControllerLocal.createNewCustomer(newCustomer1);
      customerEntityControllerLocal.createNewCustomer(newCustomer2);
      customerEntityControllerLocal.createNewCustomer(newCustomer3);
     
      
      //creating restaurant Table
      RestaurantSeating newSeat1 = restaurantSeatingControllerLocal.createNewSeating(new RestaurantSeating("No 1" , 4));   
      RestaurantSeating newSeat2 = restaurantSeatingControllerLocal.createNewSeating(new RestaurantSeating("No 2" , 4));    
      
      //creating restaurant Fridge
      Fridge newFridge =   fridgeControllerLocal.createFridge(new Fridge(0.0 , 0.0));
      
      Inventory newInventory1 = inventoryControllerLocal.createInventory(new Inventory("Salmon" , 0.20 , 5.0));
      Inventory newInventory2 = inventoryControllerLocal.createInventory(new Inventory("Cabbage" , 0.30, 5.0));
      
      
      
      Container newContainer1 = containerControllerLocal.createContainer(new Container(0.5, newInventory1));
      Container newContainer2 = containerControllerLocal.createContainer(new Container(0.5, newInventory2));
      
      
      List<Sensor> sensors = new ArrayList<>();
      Sensor newSensor1 = sensorControllerLocal.createSensor(new Sensor("Beacons" , 10.0 , newSeat1));
      Sensor newSensor2 = sensorControllerLocal.createSensor(new Sensor("Beacons" , 10.0 , newSeat2));
      
      Sensor newSensor3 = sensorControllerLocal.createSensor(new Sensor("temperatureSensor" , 0.0 , newFridge));
      Sensor newSensor4 = sensorControllerLocal.createSensor(new Sensor("LoadSensor" , 0.0, newContainer1));
      Sensor newSensor5 = sensorControllerLocal.createSensor(new Sensor("LoadSensor" , 0.0, newContainer2));
      
      sensors.add(newSensor1);
      sensors.add(newSensor2);
       sensors.add(newSensor3);
       sensors.add(newSensor4);
       sensors.add(newSensor5);

      
      List<Dish> dishes = new ArrayList<>();
      Dish newDish1 = restaurantDishControllerLocal.createDish(new Dish("Salmon Spagetti" , "Seafood" , "XXX" , 7.90));
      Dish newDish2 = restaurantDishControllerLocal.createDish(new Dish("Signature Chop" , "Meat" , "XXXAsd" ,11.90));
      Dish newDish3 = restaurantDishControllerLocal.createDish(new Dish("Veggie Sandwich" , "Vegetarian" , "ImagePathHere" ,6.90));
      dishes.add(newDish1);
      dishes.add(newDish2);
      dishes.add(newDish3);
      
      Restaurant newRest = restaurantControllerLocal.createRestaurant(new Restaurant("Restaurant A" , "65167812" , "Restaurant A Address " ,  "343231" , "restA@gmail.com" , "122453tedgyr@h9" , sensors , dishes));
      
      
      RestaurantEmployee newEmployee = restaurantEmployeeControllerLocal.createEmployee(new RestaurantEmployee("Emp A" , "Ln A" , 'M' , "EmpA@RestA.com" , "12345678" , "Manager" , newRest));
      RestaurantEmployee newEmployee2 = restaurantEmployeeControllerLocal.createEmployee(new RestaurantEmployee("Emp B", "Ln B" , 'F', "EmpB@RestA.com" , "12345678" , "Kicthen" , newRest ));
      
      
      
     // System.out.println("New Employee ID " + newEmployee.getId() + " and " + newEmployee2.getId() + " are created");
      
      //customerEntityControllerLocal.createNewCustomer(newCustomer);

    }
    
    
}
