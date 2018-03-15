/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodemblemclient;

import ejb.session.stateless.testingEntityControllerRemote;
import javax.ejb.EJB;

/**
 *
 * @author David
 */
public class Main {

    @EJB(name = "testingEntityControllerRemote")
    private static testingEntityControllerRemote testingEntityControllerRemote;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        
    }
    
}
