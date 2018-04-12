/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import datamodel.ws.CustomerOrderReq;
import datamodel.ws.CustomerOrderRsp;
import datamodel.ws.RegisterCustomerReq;
import datamodel.ws.RetrieveCustomerAccountRsp;
import datamodel.ws.UpdateRestaurantCustomerOrderCookedReq;
import datamodel.ws.UpdateRestaurantCustomerOrderCookedRsp;
import ejb.session.stateless.CustomerEntityControllerLocal;
import entity.Customer;
import entity.OrderDish;
import entity.RestaurantCustomerOrder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

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
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("RegisterCustomer")
    public Response RegisterCustomer(JAXBElement<RegisterCustomerReq> jaxbregcustReq)
    {
         
          if((jaxbregcustReq != null) && (jaxbregcustReq.getValue() != null))
          {
            try
            {
                
                RegisterCustomerReq regReq = jaxbregcustReq.getValue();
                Customer c = regReq.getCustomer();
                Customer newcust = customerEntityController.createNewCustomer(c);
                System.out.println("Customer " + newcust.getEmail() + " created");         
                return Response.status(Response.Status.OK).entity(new RegisterCustomerReq(newcust)).build(); 
            }
            catch (Exception ex){
                 ex.printStackTrace();
                 return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
          }
          else
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid register request").build();
        }
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
            
            if(information == null)
                return Response.status(Response.Status.OK).entity(new RetrieveCustomerAccountRsp(false)).build();
 
            
            return Response.status(Response.Status.OK).entity(new RetrieveCustomerAccountRsp(true)).build();
 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(new RetrieveCustomerAccountRsp(false)).build();
        }
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("AddCustomerOrder")
    public Response addCustomerOrder(JAXBElement<CustomerOrderReq> jaxbaddCustomerOrderReq)
    {
         
          if((jaxbaddCustomerOrderReq != null) && (jaxbaddCustomerOrderReq.getValue() != null))
          {
            try
            {
                
                CustomerOrderReq custOrderReq = jaxbaddCustomerOrderReq.getValue();
                String email = custOrderReq.getEmail();
                List<OrderDish>orderdishes = custOrderReq.getOrderdishes();
                Double total = custOrderReq.getTotal();
                RestaurantCustomerOrder restcustorder = customerEntityController.addCustomerOrder(email, orderdishes, total);             
                System.out.println("New Customer Order ID = " + restcustorder.getId());         
                return Response.status(Response.Status.OK).entity(new CustomerOrderRsp(restcustorder)).build(); 
            }
            catch (Exception ex){
                 ex.printStackTrace();
                 return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
          }
          else
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid register request").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updateCustomerOrder")
    public Response updateCustomerOrder(JAXBElement<UpdateRestaurantCustomerOrderCookedReq> jaxbUpdateRestaurantCustomerOrderCookedReq)
    {
        System.out.println("CustomerResource.updateCustomerOrder() called");
        if((jaxbUpdateRestaurantCustomerOrderCookedReq !=null) && (jaxbUpdateRestaurantCustomerOrderCookedReq.getValue() != null))
        {
            try
        {
            UpdateRestaurantCustomerOrderCookedReq orderReq = jaxbUpdateRestaurantCustomerOrderCookedReq.getValue();
            long orderId = orderReq.getOrderId();
            System.out.println("GOING TO UPDATE CUSTOMER ORDER: " + orderId);
            customerEntityController.updateCustomerOrderCooked(orderId);   

            System.out.println("Updated Customer Order ID = " + orderId);         
            return Response.status(Response.Status.OK).entity(new UpdateRestaurantCustomerOrderCookedRsp(true)).build(); 
        }
        catch (Exception ex){
            ex.printStackTrace();
            return Response.status(Response.Status.OK).entity(new UpdateRestaurantCustomerOrderCookedRsp(false)).build(); 
        }
        }else{
             return Response.status(Response.Status.BAD_REQUEST).entity("Invalid register request").build();
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
