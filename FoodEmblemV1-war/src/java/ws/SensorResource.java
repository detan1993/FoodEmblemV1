/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import datamodel.ws.RetrieveRestaurantContainerRsp;
import datamodel.ws.RetrieveRestaurantFridgeRsp;
import datamodel.ws.RetrieveRestaurantSeatingRsp;
import datamodel.ws.RetrieveRestaurantSensorRsp;
import ejb.session.stateless.ContainerControllerLocal;
import ejb.session.stateless.FridgeControllerLocal;
import ejb.session.stateless.RestaurantSeatingControllerLocal;
import ejb.session.stateless.SensorControllerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * REST Web Service
 *
 * @author David
 */
@Path("Sensor")
public class SensorResource {

    RestaurantSeatingControllerLocal restaurantSeatingController = lookupRestaurantSeatingControllerLocal();

    FridgeControllerLocal fridgeController = lookupFridgeControllerLocal();

    ContainerControllerLocal containerController = lookupContainerControllerLocal();

    SensorControllerLocal sensorController = lookupSensorControllerLocal();
    
    
    
    
    
    @Context
    private UriInfo context;

    
    
    
    /**
     * Creates a new instance of SensorResource
     */
    public SensorResource() {
    }


    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getSensorsByRestaurantId/{restaurantId}")
    public Response retrieveSensorsFromRestaurant(@PathParam("restaurantId") String restaurantId)
    {
        try
        {
           
            System.out.println("********** Retrieving sensor from restaurant ID" + restaurantId);
            return Response.status(Status.OK).entity(new RetrieveRestaurantSensorRsp(sensorController.retrieveSensor(Long.parseLong(restaurantId)))).build();
 
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getContainersByRestaurantId/{restaurantId}")
    public Response retrieveContainersFromRestaurant(@PathParam("restaurantId") String restaurantId)
    {
        try
        {
           
            System.out.println("********** Retrieving Container from restaurant ID" + restaurantId);
            return Response.status(Status.OK).entity(new RetrieveRestaurantContainerRsp(containerController.retrieveContainersByRestaurantId(Long.parseLong(restaurantId)))).build();
 
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(new RetrieveRestaurantContainerRsp(null)).build();
        }
    }
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getFridgesByRestaurantId/{restaurantId}")
    public Response retrieveFridgesFromRestaurant(@PathParam("restaurantId") String restaurantId)
    {
        try
        {
           
            System.out.println("********** Retrieving Fridges from restaurant ID" + restaurantId);
            return Response.status(Status.OK).entity(new RetrieveRestaurantFridgeRsp(fridgeController.retrieveFridgesByRestaurantId(Long.parseLong(restaurantId)))).build();
 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return Response.status(Status.BAD_REQUEST).entity(new RetrieveRestaurantFridgeRsp(null)).build();
        }
    }
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getSeatsByRestaurantId/{restaurantId}")
    public Response retrieveSeatsFromRestaurant(@PathParam("restaurantId") String restaurantId)
    {
        try
        {
           
            System.out.println("********** Retrieving seats from restaurant ID" + restaurantId);
            return Response.status(Status.OK).entity(new RetrieveRestaurantSeatingRsp(restaurantSeatingController.retrieveSeatsByRestaurantId(Long.parseLong(restaurantId)))).build();
 
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(new RetrieveRestaurantSeatingRsp(null)).build();
        }
    }
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("testUpdate/{restaurantId}")
    public void updateFridge(@PathParam("restaurantId") String restaurantId)
    {
        try
        {
           
            System.out.println("********** Updating seats from restaurant ID" + restaurantId);
            fridgeController.updateTempTest(Long.parseLong(restaurantId));
 

            //return Response.status(Status.OK).entity(new RetrieveRestaurantSeatingRsp(restaurantSeatingController.retrieveSeatsByRestaurantId(Long.parseLong(restaurantId)))).build();
 
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
           // return Response.status(Status.BAD_REQUEST).entity(new RetrieveRestaurantSeatingRsp(null)).build();
        }
    }
    
    

    /**
     * PUT method for updating or creating an instance of SensorResource
     * @param content representation for the resource
     */
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    private SensorControllerLocal lookupSensorControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (SensorControllerLocal) c.lookup("java:global/FoodEmblemV1/FoodEmblemV1-ejb/SensorController!ejb.session.stateless.SensorControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ContainerControllerLocal lookupContainerControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ContainerControllerLocal) c.lookup("java:global/FoodEmblemV1/FoodEmblemV1-ejb/ContainerController!ejb.session.stateless.ContainerControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private FridgeControllerLocal lookupFridgeControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (FridgeControllerLocal) c.lookup("java:global/FoodEmblemV1/FoodEmblemV1-ejb/FridgeController!ejb.session.stateless.FridgeControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private RestaurantSeatingControllerLocal lookupRestaurantSeatingControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (RestaurantSeatingControllerLocal) c.lookup("java:global/FoodEmblemV1/FoodEmblemV1-ejb/RestaurantSeatingController!ejb.session.stateless.RestaurantSeatingControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
