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
import ejb.session.stateless.OrderDishControllerLocal;
import ejb.session.stateless.PromotionControllerLocal;
import ejb.session.stateless.ReservationControllerLocal;
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
import entity.OrderDish;
import entity.Promotion;
import entity.Reservation;
import entity.Restaurant;
import entity.RestaurantCustomerOrder;
import entity.RestaurantEmployee;
import entity.RestaurantSeating;
import entity.Sensor;
import java.util.ArrayList;
import java.util.Date;
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
    
    @EJB(name = "PromotionControllerLocal")
    private PromotionControllerLocal promotionControllerLocal;
    
    @EJB(name = "OrderDishControllerLocal")
    private OrderDishControllerLocal orderDishControllerLocal;
    
    @EJB(name="ReservationControllerLocal")
    private ReservationControllerLocal reservationControllerLocal;
    

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
      Sensor newSensor1 = sensorControllerLocal.createSensor(new Sensor("33083_34852" , "Beacon" , 10.0 , newSeat1, "Restaurant Seating"));
      //sensorControllerLocal.createSensor(new Sensor("47712_24497","Promotion" , 15.0, "This is promotion sensor used by Restaurant A"));
    
     //   Sensor newSensor1 =sensorControllerLocal.createSensor(new Sensor("47712_244497" , "Beacon" , 10.0 , newSeat1, "Restaurant Seating"  ));
      //sensorControllerLocal.createSensor(new Sensor("Beacons 1 ID" , "Beacon" , 10.0 , newSeat1, 47712, 24497));
      Sensor newSensor2 =  sensorControllerLocal.createSensor(new Sensor("21187_39875" , "Beacon" , 10.0 , newSeat2, "Restaurant Seating"  ));
    //sensorControllerLocal.createSensor(new Sensor("Beacpns 2 ID", "Beacon" , 10.0 , newSeat2, 21187,39875));
      
      Sensor newSensor3 = sensorControllerLocal.createSensor(new Sensor("D3:8D:51:5B:43:37" , "Microbit" , 7.0 , newFridge, "Temperature Sensor"));
      Sensor newSensor4 = sensorControllerLocal.createSensor(new Sensor("LoadCellID1" , "LoadCellName" , 0.0 , newContainer1, "Weight Sensor"));
      //sensorControllerLocal.createSensor(new Sensor("LoadSensor" , 0.0, newContainer1, 0, 0));
      Sensor newSensor5 = sensorControllerLocal.createSensor(new Sensor("LoadCellID2" , "LoadCellName" , 0.0 , newContainer2, "Weight Sensor"));
              //= sensorControllerLocal.createSensor(new Sensor("LoadSensor" , 0.0, newContainer2, 0, 0));
      

      
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
       //creating promotion for newRest
      String desc = "Enjoy 30% for all dinner items only from 8pm to 10pm!";
      Promotion promotion = promotionControllerLocal.createPromotion(new Promotion(desc,30.00,new Date(),new Date(),newRest ));
      Sensor promoSensor = sensorControllerLocal.createSensor(new Sensor("47712_24497","Promotion" , 15.0, "This is promotion sensor used by Restaurant A"));
      RestaurantEmployee newEmployee = restaurantEmployeeControllerLocal.createEmployee(new RestaurantEmployee("Emp A" , "Ln A" , 'M' , "EmpA@RestA.com" , "12345678" , "Manager" , newRest));
      RestaurantEmployee newEmployee2 = restaurantEmployeeControllerLocal.createEmployee(new RestaurantEmployee("Emp B", "Ln B" , 'F', "EmpB@RestA.com" , "12345678" , "Kicthen" , newRest ));
      newRest.getSensors().add(promoSensor);
      
      
      Reservation reservation = new Reservation(new Date(), "Active", 4, 4, null, newSeat2);
      reservationControllerLocal.createReservation(reservation, newCustomer1.getEmail(), newSeat1.getId());
      
      RestaurantCustomerOrder order = new RestaurantCustomerOrder(49.95, new Date(), null, null,false);
      customerEntityControllerLocal.addCustomerOrder(order);

      OrderDish od1 = new OrderDish(3,newDish1.getId());
      OrderDish od2 = new OrderDish(1,newDish1.getId());
      
      orderDishControllerLocal.createOrderDish(od1);
      orderDishControllerLocal.createOrderDish(od2);
      
      List<OrderDish> orderDishList = new ArrayList<OrderDish>();
      orderDishList.add(od1);
      orderDishList.add(od2);
      order.setOrderDishes(orderDishList);
      
      newDish1.setOrderDishes(orderDishList);
      
      List<RestaurantCustomerOrder> orderList = new ArrayList<RestaurantCustomerOrder>();
      orderList.add(order);
      reservation.setRestCustOrders(orderList);
      
      


      
     // System.out.println("New Employee ID " + newEmployee.getId() + " and " + newEmployee2.getId() + " are created");
      
      //customerEntityControllerLocal.createNewCustomer(newCustomer);

    }
    
    
}
