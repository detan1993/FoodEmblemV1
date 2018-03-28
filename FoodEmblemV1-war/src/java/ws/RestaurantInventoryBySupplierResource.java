/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import datamodel.ws.RetrieveRestaurantInvenotoriesReq;
import datamodel.ws.RetrieveRestaurantInventoriesRsp;
import ejb.session.stateless.ReservationControllerLocal;
import ejb.session.stateless.RestaurantControllerLocal;
import entity.Restaurant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

/**
 * REST Web Service
 *
 * @author David
 */
@Path("RestaurantInventoryBySupplier")
public class RestaurantInventoryBySupplierResource {

    RestaurantControllerLocal restaurantController = lookupRestaurantControllerLocal();

    @Context
    private UriInfo context;
    
    

    /**
     * Creates a new instance of RestaurantInventoryBySupplierResource
     */
    public RestaurantInventoryBySupplierResource() {
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPartnerRestaurant(JAXBElement<RetrieveRestaurantInvenotoriesReq> jaxbPartnerRestReq)
    {   
        System.out.println("INSIDE PARTNER REST");
        
          if((jaxbPartnerRestReq != null) && (jaxbPartnerRestReq.getValue() != null))
          {
            try
            {
                              
                RetrieveRestaurantInvenotoriesReq restInventory = jaxbPartnerRestReq.getValue();
                
                List<String> partnerRestaurantKeys = restInventory.getListOfRestaurantKey();
                
                for(String a :  partnerRestaurantKeys)
                    System.out.println("*********************" + a);
              
                System.err.println("********** Calling Partner Restaurant method");
                List<Restaurant> partnerRestInformation = restaurantController.retrievePartnerRestaurant(partnerRestaurantKeys);
                
                if(!partnerRestInformation.isEmpty())               
                return Response.status(Response.Status.OK).entity(new RetrieveRestaurantInventoriesRsp(partnerRestInformation)).build();
                else
                    return Response.status(Response.Status.OK).entity(new RetrieveRestaurantInventoriesRsp()).build();  //this is suppose to return other messahe. Not complete yet.
                         
            }
            catch(Exception ex)
            {
                System.out.println("error" + ex);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
        else
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid register request").build();
        }
    }

    private ReservationControllerLocal lookupReservationControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ReservationControllerLocal) c.lookup("java:global/FoodEmblemV1/FoodEmblemV1-ejb/ReservationController!ejb.session.stateless.ReservationControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

   

    /**
     * PUT method for updating or creating an instance of RestaurantInventoryBySupplierResource
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
