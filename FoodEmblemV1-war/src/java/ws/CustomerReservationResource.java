/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import datamodel.ws.ReservationReq;
import datamodel.ws.ReservationRsp;
import ejb.session.stateless.ReservationControllerLocal;
import entity.Reservation;
import java.util.Date;
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
@Path("CustomerReservation")
public class CustomerReservationResource {

    ReservationControllerLocal reservationController = lookupReservationControllerLocal();

    @Context
    private UriInfo context;
    
    

    /**
     * Creates a new instance of CustomerReservationResource
     */
    public CustomerReservationResource() {
    }

   
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getReservation/{email}/")
    public Response retrieveReservation(@PathParam("email") String email)
    {
        
        //to do later. This is gettting reservation by customer ID
       /* try
        {
           
            System.out.println("********** Login Attempt" + email + " password = " +  password);
             RestaurantEmployee information = restaurantEmployeeController.login(email, password);
            //System.out.println(information);
            return Response.status(Response.Status.OK).entity(new RetrieveEmployeeAccountRsp(information.getEmail() , information.getFirstName() + " " + information.getLastName(), information.getRestaurant().getId().toString(), information.getGender(), information.getRestaurantRole())).build();
 
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(new RetrieveEmployeeAccountRsp("" , "" , "" , 'N' , "")).build();
        }*/
        return null;
    }
    
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response reserveSeat(JAXBElement<ReservationReq> jaxbReservationReq)
    {
         
          if((jaxbReservationReq != null) && (jaxbReservationReq.getValue() != null))
          {
            try
            {
                              
                ReservationReq reservationReq = jaxbReservationReq.getValue();
                
                String customerId = reservationReq.getEmail();
                Long seatId =  reservationReq.getSeatId();
                System.err.println("********** Calling reservation method. Customer email is " + customerId  + " seatId = " + seatId);
                Reservation newReservation = reservationController.createReservation(new Reservation(new Date(), "Active" , reservationReq.getNoOfPax(), 0), customerId, seatId);
                System.out.println("new reservation ID is " +  newReservation.getId());
                
                return Response.status(Response.Status.OK).entity(new ReservationRsp(newReservation)).build();
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
}
