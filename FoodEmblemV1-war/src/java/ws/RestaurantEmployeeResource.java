/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import datamodel.ws.RetrieveEmployeeAccountRsp;
import ejb.session.stateless.RestaurantEmployeeControllerLocal;
import entity.RestaurantEmployee;
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
@Path("RestaurantEmployee")
public class RestaurantEmployeeResource {

    RestaurantEmployeeControllerLocal restaurantEmployeeController = lookupRestaurantEmployeeControllerLocal();

    @Context
    private UriInfo context;
    
    
    

    /**
     * Creates a new instance of RestaurantEmployeeResource
     */
    public RestaurantEmployeeResource() {
    }
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("login/{email}/{password}")
    public Response login(@PathParam("email") String email, @PathParam("password") String password )
    {
        try
        {
           
            System.out.println("********** Login Attempt" + email + " password = " +  password);
             RestaurantEmployee information = restaurantEmployeeController.login(email, password);
             
             if(information == null)
                  return Response.status(Response.Status.OK).entity(new RetrieveEmployeeAccountRsp("" , "" , "" , 'N' , "")).build();
             
             //System.out.println(information);
             return Response.status(Response.Status.OK).entity(new RetrieveEmployeeAccountRsp(information.getEmail() , information.getFirstName() + " " + information.getLastName(), information.getRestaurant().getId().toString(), information.getGender(), information.getRestaurantRole())).build();
 
        }
        catch(Exception ex) 
        {
            System.err.println(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(new RetrieveEmployeeAccountRsp("" , "" , "" , 'N' , "")).build();
        }
    }
    
    

    /**
     * PUT method for updating or creating an instance of RestaurantEmployeeResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    private RestaurantEmployeeControllerLocal lookupRestaurantEmployeeControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (RestaurantEmployeeControllerLocal) c.lookup("java:global/FoodEmblemV1/FoodEmblemV1-ejb/RestaurantEmployeeController!ejb.session.stateless.RestaurantEmployeeControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
