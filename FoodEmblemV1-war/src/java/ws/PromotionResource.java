/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import datamodel.ws.PromotionReq;
import datamodel.ws.ReservationRsp;
import datamodel.ws.RetrieveRestaurantPromotionRsp;
import ejb.session.stateless.PromotionControllerLocal;
import entity.Promotion;
import entity.Reservation;
import java.util.Date;
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
import javax.xml.bind.JAXBElement;

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
    @Path("retrieveRestaurantPromoFromBeacon/{sensorId}")
     public Response retrieveRestaurantPromoFromBeacon(@PathParam("sensorId") String sensorId){
         try{
             Promotion promo = promotionController.retrieveRestaurantPromoFromBeacon(sensorId);
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
     
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deletePromotion/{promoid}")
     public Response deletePromotion(@PathParam("promoid")long promotionId){
         try {
             Boolean deletesuccess = promotionController.deletePromotion(promotionId);
             System.out.println("Promo ID " + promotionId + " deleted" );
                return Response.status(Response.Status.OK).entity(new RetrieveRestaurantPromotionRsp(deletesuccess)).build();   
         }
         catch (Exception ex){
             ex.printStackTrace();
             return Response.status(Response.Status.BAD_REQUEST).entity(new RetrieveRestaurantPromotionRsp()).build();
         }
     }
     
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updatePromotion")
    public Response updatePromotion(JAXBElement<PromotionReq> jaxbUpdatePromotionReq)
    {
         
          if((jaxbUpdatePromotionReq != null) && (jaxbUpdatePromotionReq.getValue() != null))
          {
            try
            {
                
                PromotionReq promotionReq = jaxbUpdatePromotionReq.getValue();
                Promotion promotoupdate = promotionReq.getPromotion();
                System.err.println("********** Calling update Promotion method");
                Promotion updatedPromo = promotionController.updatePromotion(promotoupdate);
                System.out.println("Promo updated");
                return Response.status(Response.Status.OK).entity(new RetrieveRestaurantPromotionRsp(updatedPromo)).build();
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
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("createRestaurantPromotion/{restid}")
    public Response createRestaurantPromotion(JAXBElement<PromotionReq> jaxbCreatePromotionReq, @PathParam("restid")long restaurantid)
    {
         
          if((jaxbCreatePromotionReq != null) && (jaxbCreatePromotionReq.getValue() != null))
          {
            try
            {
                
                PromotionReq promotionReq = jaxbCreatePromotionReq.getValue();
                Promotion promotocreate = promotionReq.getPromotion();
                System.err.println("********** Calling create Promotion method");
                Promotion createdpromo = promotionController.createRestaurantPromotion(promotocreate,restaurantid);
                System.out.println("Promo created");
                return Response.status(Response.Status.OK).entity(new RetrieveRestaurantPromotionRsp(createdpromo)).build();
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
