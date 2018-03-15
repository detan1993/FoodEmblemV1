/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import datamodel.ws.RetrieveCustomerAccountRsp;
import ejb.session.stateless.CustomerEntityControllerLocal;
import entity.Customer;
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
@Path("Customer")
public class CustomerResource {

    CustomerEntityControllerLocal customerEntityController = lookupCustomerEntityControllerLocal();

    @Context
    private UriInfo context;

    
    /**
     * Creates a new instance of CustomerResource
     */
    public CustomerResource() {
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
            Customer information = customerEntityController.login(email, password);
            return Response.status(Response.Status.OK).entity(new RetrieveCustomerAccountRsp(information.getEmail() , information.getName())).build();
 
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(new RetrieveCustomerAccountRsp("", "")).build();
        }
    }

    /**
     * PUT method for updating or creating an instance of CustomerResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    private CustomerEntityControllerLocal lookupCustomerEntityControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CustomerEntityControllerLocal) c.lookup("java:global/FoodEmblemV1/FoodEmblemV1-ejb/CustomerEntityController!ejb.session.stateless.CustomerEntityControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
