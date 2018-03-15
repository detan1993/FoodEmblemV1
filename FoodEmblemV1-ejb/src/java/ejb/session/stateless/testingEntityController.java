/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.TestingEntity;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author David
 */
@Stateless
@Local(testingEntityControllerLocal.class)
@Remote(testingEntityControllerRemote.class)
public class testingEntityController implements testingEntityControllerRemote, testingEntityControllerLocal {

    @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public TestingEntity createTest(TestingEntity newTest)
    {
        em.persist(newTest);
        em.flush();
        em.refresh(newTest);
        return newTest;
    }
}
