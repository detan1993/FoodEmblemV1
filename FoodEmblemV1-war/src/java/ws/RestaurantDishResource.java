/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import datamodel.ws.RetrieveRestaurantDishRsp;
import ejb.session.stateless.RestaurantDishControllerLocal;
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

/**
 * REST Web Service
 *
 * @author David
 */
@Path("RestaurantDish")
public class RestaurantDishResource {

    RestaurantDishControllerLocal restaurantDishController = lookupRestaurantDishControllerLocal();

    @Context
    private UriInfo context;
    
    
    
    

    /**
     * Creates a new instance of RestaurantDishResource
     */
    public RestaurantDishResource() {
        
    }
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{restaurantId}")
    public Response retrieveDishesFromRestaurant(@PathParam("restaurantId") String restaurantId)
    {
        try
        {
           
            System.out.println("********** Dish Resources() Retrieving Dishes from restaurant ID" + restaurantId);
            return Response.status(Response.Status.OK).entity(new RetrieveRestaurantDishRsp(restaurantDishController.getDishes(Long.parseLong(restaurantId)))).build();
 
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    
    //this is testinh  
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{restaurantId}/{hello}")
    public Response retrieveTest(@PathParam("restaurantId") String restaurantId , @PathParam("hello") String hello)
    {
       
            System.out.println("********** Test" + restaurantId + " hello" + hello);
            return Response.status(Response.Status.OK).entity("Testing is called "  + hello).build();
 
       
    }
    

    /**
     * PUT method for updating or creating an instance of RestaurantDishResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
        
        
        
    }

    private RestaurantDishControllerLocal lookupRestaurantDishControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (RestaurantDishControllerLocal) c.lookup("java:global/FoodEmblemV1/FoodEmblemV1-ejb/RestaurantDishController!ejb.session.stateless.RestaurantDishControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
