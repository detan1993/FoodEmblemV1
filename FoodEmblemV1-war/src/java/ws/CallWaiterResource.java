/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import datamodel.ws.CallWaiterReq;
import datamodel.ws.CallWaiterRsp;
import ejb.session.stateless.CallWaiterControllerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author JiongYi
 */
@Path("CallWaiter")
public class CallWaiterResource {
    CallWaiterControllerLocal callwaitercontroller = lookupCallWaiterControllerLocal();
    @Context
    private UriInfo context;
    
    
    public CallWaiterResource(){
        
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Produces(MediaType.APPLICATION_JSON)
     public Response callWaiter(JAXBElement<CallWaiterReq> jaxbUpdateCallWaiterReq)
    {
         
          if((jaxbUpdateCallWaiterReq != null) && (jaxbUpdateCallWaiterReq.getValue() != null))
          {
            try
            {
                
                CallWaiterReq promotionReq = jaxbUpdateCallWaiterReq.getValue();
                String tableid = promotionReq.getTableid();
                Boolean callsuccess = callwaitercontroller.requestWaiter(tableid);
                return Response.status(Response.Status.OK).entity(new CallWaiterRsp(callsuccess)).build();
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
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    private CallWaiterControllerLocal lookupCallWaiterControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CallWaiterControllerLocal) c.lookup("java:global/FoodEmblemV1/FoodEmblemV1-ejb/CallWaiterController!ejb.session.stateless.CallWaiterControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
