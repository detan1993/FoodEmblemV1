/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import datamodel.ws.RetrieveRestaurantsRsp;
import ejb.session.stateless.RestaurantControllerLocal;
import entity.Restaurant;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
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
@Path("Restaurant")
public class RestaurantResource {

    RestaurantControllerLocal restaurantController = lookupRestaurantControllerLocal();

    @Context
    private UriInfo context;
    
    

    /**
     * Creates a new instance of RestaurantResource
     */
    public RestaurantResource() {
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllRestaurants()
    {
        try
        {
           
            System.out.println("********** retrieving restaurnt ********");
            return Response.status(Response.Status.OK).entity(new RetrieveRestaurantsRsp(restaurantController.retrieveRestaurant())).build();
 
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(new RetrieveRestaurantsRsp()).build();
        }
    }
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getRestaurantById/{restid}")
    public Response retrieveRestaurantById(@PathParam("restid") int restid){
        try {
            Restaurant r = restaurantController.retrieveRestaurantById(restid);
            System.out.print("Returning " + r.getName());
            return Response.status(Response.Status.OK).entity(new RetrieveRestaurantsRsp(r)).build();
        }
        catch (Exception ex)
        {
            System.err.print(ex.toString());
            return Response.status(Response.Status.BAD_REQUEST).entity(new RetrieveRestaurantsRsp()).build();
        }
    }


    /**
     * PUT method for updating or creating an instance of RestaurantResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    private RestaurantControllerLocal lookupRestaurantControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (RestaurantControllerLocal) c.lookup("java:global/FoodEmblemV1/FoodEmblemV1-ejb/RestaurantController!ejb.session.stateless.RestaurantControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
