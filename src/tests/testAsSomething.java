package tests;


import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.beans.Person;
import database.services.imp.LoginManager;
import database.services.imp.PersonManager;

public class testAsSomething {

    @EJB
    PersonManager personManager;
    
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
    	personManager.newPerson("2tota", "tata", "titi", "vasavoir");
        if(toto.login("t8to", "vasavoir")!= null)
        	personManager.newPerson("3tota", "tata", "titi", "vasavoir");
    }
	
    @Test
    public void testFindCourses2() {
		Person person = new Person();
		person.seteMail("t12to");
		person.setName("tata");
		person.setSurname("titi");
		person.setPassword("vasavoir");
		personManager.newPerson(person);
		if(toto==null)
			System.out.println("tarace");
        if(toto.login("t8to", "vasavoir")!= null)
        	personManager.newPerson("3tota", "tata", "titi", "vasavoir");
    }
    
    @Test
    public void testFindCourses3() {
		System.out.println(personManager.searchPerson("toto")); 
    }
    
}
