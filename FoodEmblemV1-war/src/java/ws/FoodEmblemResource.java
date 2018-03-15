/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import ejb.session.stateless.testingEntityControllerLocal;
import entity.TestingEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author David
 */
@Path("FoodEmblemTesting")
public class FoodEmblemResource {

    testingEntityControllerLocal testingEntityController = lookuptestingEntityControllerLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FoodEmblemResource
     */
    public FoodEmblemResource() {
    }

    /**
     * Retrieves representation of an instance of ws.FoodEmblemResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllTesting() {
        //TODO return proper representation object
        
        System.out.println("getXML()");
        /*TestingEntity a = new TestingEntity("FirstTest" , "This is the first Test" ,  new Date());
        testingEntityController.createTest(a);*/
         List<TestingEntity> test = new ArrayList<>();
        
        test.add(new TestingEntity("test 2 ", "this is test 2" , new Date()));
        test.add(new TestingEntity ("test 3" , "This is test 3" , new Date()));

       // return Response.status(Status.OK).entity(new RetrieveAllProductsRsp(products)).build();
       return Response.status(Response.Status.OK).build();
        
        
        //throw new UnsupportedOperationException();
        
    }

    /**
     * PUT method for updating or creating an instance of FoodEmblemResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
        

    }

    private testingEntityControllerLocal lookuptestingEntityControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (testingEntityControllerLocal) c.lookup("java:global/FoodEmblemV1/FoodEmblemV1-ejb/testingEntityController!ejb.session.stateless.testingEntityControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
