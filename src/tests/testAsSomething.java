package tests;


import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.beans.Person;
import database.services.imp.LoginManager;
import database.services.proxy.AsAnonymous;

public class testAsSomething {

    @EJB
    AsAnonymous AsAnonymous;
    
    @EJB
    LoginManager toto;

    @Before
    public void setUp() throws Exception {
        EJBContainer.createEJBContainer().getContext().bind("inject", this);
        System.out.println("start");
    }

    @After
    public void tearDown() throws Exception {
        EJBContainer.createEJBContainer().close();
        System.out.println("stop");
    }
    
    @Test
    public void testFindCourses() throws NamingException {
    	AsAnonymous.newPerson("2tota", "tata", "titi", "vasavoir");
        if(toto.login("t8to", "vasavoir")!= null)
        	AsAnonymous.newPerson("3tota", "tata", "titi", "vasavoir");
    }
	
    @Test
    public void testFindCourses2() {
    	AsAnonymous.newPerson("34tota", "tata", "titi", "vasavoir");
		if(toto==null)
			System.out.println("tarace");
        if(toto.login("t8to", "vasavoir")!= null)
        	AsAnonymous.newPerson("3tota", "tata", "titi", "vasavoir");
    }
    
    @Test
    public void testFindCourses3() {
//		System.out.println(AsAnonymous.searchPerson("t8to")); 
    }
    
}
