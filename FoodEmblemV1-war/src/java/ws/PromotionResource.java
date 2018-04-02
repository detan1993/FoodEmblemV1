/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import datamodel.ws.RetrieveRestaurantPromotionRsp;
import ejb.session.stateless.PromotionControllerLocal;
import entity.Promotion;
import java.util.List;
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
@Path("Promotion")
public class PromotionResource {

    PromotionControllerLocal promotionController = lookupPromotionControllerLocal();

    @Context
    private UriInfo context;
    
    

    /**
     * Creates a new instance of PromotionResource
     */
    public PromotionResource() {
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getPromotionByRestaurantId/{restaurantId}")
    public Response retrievePromotionFromRestaurant(@PathParam("restaurantId") String restaurantId)
    {
        try
        {
           
            System.out.println("********** Retrieving Promotion from restaurant ID" + restaurantId);
            List<Promotion> promo = promotionController.retrievePromotionsByRestaurantId(Long.parseLong(restaurantId));
            System.out.println(" Promo from Restaurant is " + promo.size());
            return Response.status(Response.Status.OK).entity(new RetrieveRestaurantPromotionRsp(promo)).build();
 
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(new RetrieveRestaurantPromotionRsp()).build();
        }
    }
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("retrieveRestaurantPromoFromBeacon/{major}/{minor}")
     public Response retrieveRestaurantPromoFromBeacon(@PathParam("major") int major, @PathParam("minor") int minor){
         try{
             Promotion promo = promotionController.retrieveRestaurantPromoFromBeacon(major, minor);
             if (promo != null){
               System.out.println("Promo desc is " + promo.getDescription());
             return Response.status(Response.Status.OK).entity(new RetrieveRestaurantPromotionRsp(promo)).build();   
             }
             else {
                    return Response.status(Response.Status.BAD_REQUEST).entity(new RetrieveRestaurantPromotionRsp()).build();
             }
         }
         catch (Exception ex){
             ex.printStackTrace();
              return Response.status(Response.Status.BAD_REQUEST).entity(new RetrieveRestaurantPromotionRsp()).build();
         } 
     }
    /**
     * PUT method for updating or creating an instance of PromotionResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    private PromotionControllerLocal lookupPromotionControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PromotionControllerLocal) c.lookup("java:global/FoodEmblemV1/FoodEmblemV1-ejb/PromotionController!ejb.session.stateless.PromotionControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
