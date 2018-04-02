/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import datamodel.ws.CheckHasReservation;
import datamodel.ws.ReservationReq;
import datamodel.ws.ReservationRsp;
import datamodel.ws.RetrieveCustomerReservationsRsp;
import ejb.session.stateless.CustomerEntityControllerLocal;
import ejb.session.stateless.ReservationControllerLocal;
import ejb.session.stateless.RestaurantSeatingControllerLocal;
import entity.Reservation;
import entity.RestaurantSeating;
import entity.Sensor;
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
@Path("CustomerReservation")
public class CustomerReservationResource {

    ReservationControllerLocal reservationController = lookupReservationControllerLocal();
RestaurantSeatingControllerLocal restaurantseatingcontroller = lookupRestaurantSeatingControllerLocal();
CustomerEntityControllerLocal customerentitycontroller = lookupCustomerEntityControllerLocal();
    
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
    @Path("getReservations/{email}/{status}")
    public Response retrieveReservation(@PathParam("email") String email, @PathParam("status") String status)
    { 
        try {
        List<Reservation>customerreservations = reservationController.getCustomerReservations(email,status);
        List<String>restNames = reservationController.getRestaurantNameFromReservation(customerreservations);
         System.out.println("********** Calling retrieveReservation method. List size for " + email + " is " + customerreservations.size());
     
         return Response.status(Response.Status.OK).entity(new RetrieveCustomerReservationsRsp(customerreservations,restNames)).build();
        } 
        catch (Exception ex){
            System.err.println(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(new RetrieveCustomerReservationsRsp(null,null)).build();
        }
    }
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("retrieveAllocatedSeat/{restid}/{pax}")
    public Response retrieveAllocatedSeat(@PathParam("restid") int restid, @PathParam("pax")int pax){
        try {
            RestaurantSeating rs = restaurantseatingcontroller.retrieveAllocatedSeat(restid, pax);
            System.out.println("Table returned: "+rs.getTableNo());
            return Response.status(Response.Status.OK).entity(new RetrieveCustomerReservationsRsp(rs)).build();
        }
        catch (Exception ex){
             System.err.println(ex.getMessage());
              return Response.status(Response.Status.BAD_REQUEST).entity(new RetrieveCustomerReservationsRsp(null)).build();
        }
    }
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("checkHasReservation/{email}")
    public Response checkHasReservation(@PathParam("email")String email){
        try {
            Boolean status = reservationController.checkCustomerHasReservation(email);
            return Response.status(Response.Status.OK).entity(new CheckHasReservation(status)).build();
        }
        catch (Exception ex){
            System.err.println(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(new CheckHasReservation(null)).build();
        }
    }
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("retrieveReservationSeating/{email}")
    public Response retrieveReservationSeating(@PathParam("email")String email){
        try {
            Sensor s = customerentitycontroller.retrieveReservationSeating(email);
            System.out.println("Major and minor of seating is: " + s.getMajor() + " and " + s.getMinor());
            return Response.status(Response.Status.OK).entity(new ReservationRsp(s)).build();
        }
        catch (Exception ex){
            System.err.println(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(new ReservationRsp()).build();
        }
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
    
    private RestaurantSeatingControllerLocal lookupRestaurantSeatingControllerLocal(){
          try {
            javax.naming.Context c = new InitialContext();
            return (RestaurantSeatingControllerLocal) c.lookup("java:global/FoodEmblemV1/FoodEmblemV1-ejb/RestaurantSeatingController!ejb.session.stateless.RestaurantSeatingControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
     private CustomerEntityControllerLocal lookupCustomerEntityControllerLocal(){
          try {
            javax.naming.Context c = new InitialContext();
            return (CustomerEntityControllerLocal) c.lookup("java:global/FoodEmblemV1/FoodEmblemV1-ejb/CustomerEntityController!ejb.session.stateless.CustomerEntityControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
