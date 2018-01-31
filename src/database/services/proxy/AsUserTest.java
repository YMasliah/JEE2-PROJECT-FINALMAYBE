/**
 * 
 */
package database.services.proxy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import database.beans.Activity;
import database.beans.CV;
import database.beans.Person;
import database.services.imp.CVManager;
import database.services.imp.PersonManager;
import database.services.imp.PersonManager.Param;

/**
 * @author masliah yann
 *
 */
public class AsUserTest {

	@EJB
	AsUser manager;

	/**
	 * @throws NamingException 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws NamingException{
		EJBContainer.createEJBContainer().getContext().bind("inject", this);
        assertNotNull(manager);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() {
		EJBContainer.createEJBContainer().close();
        System.out.println("stop");
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#editParam(database.beans.Person, database.services.IPersonManager.Param, java.lang.Object)}.
	 */
	@Test
	public void testEditParam() {
		Person person = manager.getPersonByParam(Param.eMail, "unique").get(0);
		manager.editParam(person, Param.surname, "osef");
		Assert.assertTrue(manager.getPersonByParam(Param.surname, "osef").size() >0);
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#getParam(database.beans.Person, database.services.IPersonManager.Param)}.
	 */
	@Test
	public void testGetParam() {
		Person person = manager.getPersonByParam(Param.eMail, "unique").get(0);
		String result = manager.getParam(person, Param.eMail);
		Assert.assertEquals(result, "unique");
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#getParams(database.beans.Person)}.
	 */
	@Test
	public void testGetParams() {
		Person person = manager.getPersonByParam(Param.eMail, "unique").get(0);
		Assert.assertEquals(person, manager.getParams(person));
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#setParams(database.beans.Person, database.beans.Person)}.
	 */
	@Test
	public void testSetParams() {
		Person person = manager.getPersonByParam(Param.eMail, "unique").get(0);
		person.setSurname("aucune idee");
		manager.setParams(null,person );
		Assert.assertEquals(person, manager.getParams(person));
		
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#deletePerson(database.beans.Person)}.
	 */
	@Test
	public void testDeletePerson() {
		manager.newPerson("pasunique", "pasunique", "pasunique", "pasunique");
		Person person = manager.getPersonByParam(Param.eMail, "pasunique").get(0);
		manager.deletePerson(person);
		Assert.assertTrue(manager.getPersonByParam(Param.eMail, "pasunique").isEmpty());
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#getPersonByParam(database.services.IPersonManager.Param, java.lang.Object)}.
	 */
	@Test
	public void testGetPersonByParam() {
		List<Person> personnes = manager.getPersonByParam(PersonManager.Param.name, "unique");
		Assert.assertEquals(personnes.size(), 1);
	}
	
	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#getPersonByParam(database.services.IPersonManager.Param, java.lang.Object)}.
	 */
	@Test
	public void test1GetPersonByParam() {
		List<Person> personnes = manager.getPersonByParam(PersonManager.Param.name, "deux");
		Assert.assertEquals(personnes.size(), 2);
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#newPerson(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	@Ignore
	public void testNewPerson() {
		manager.newPerson("toto98", "tata", "titi", "vasavoir");
		List<Person> personnes = manager.getPersonByParam(PersonManager.Param.eMail, "toto");
		Assert.assertEquals(personnes.size(), 1);
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#login(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLogin() {
		Person person = manager.login("unique", "vasavoir");
		Assert.assertNotNull(person);
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#logout(database.beans.Person)}.
	 */
	@Test
	public void testLogout() {
		manager.logout(null);
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#getCV(database.beans.Person)}.
	 */
	@Test
	public void testGetCVPerson() {
		manager.getCV((Person)null);
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#addActivity(database.beans.Person, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddActivityPersonIntegerStringStringStringString() {
		manager.addActivity(null, null, null, null, null, null);
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#addActivity(database.beans.Person, database.beans.Activity)}.
	 */
	@Test
	public void testAddActivityPersonActivity() {
		manager.addActivity(null, null);
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#updateActivity(database.beans.Person, java.lang.Integer, database.services.ICVManager.Param, java.lang.Object)}.
	 */
	@Test
	public void testUpdateActivity() {
		manager.updateActivity(null, null, null, null);
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#deleteActivity(database.beans.Person, java.lang.Integer)}.
	 */
	@Test
	public void testDeleteActivity() {
		manager.deleteActivity(null, null);
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#getCVbyPersonId(java.lang.Integer)}.
	 */
	@Test
	@Ignore
	public void testGetCVbyPersonId() {
		
		fail();
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#getActivitybyParam(database.services.ICVManager.Param, java.lang.Object)}.
	 */
	@Test
	public void testGetActivitybyParam() {
		List<Activity> activities = manager.getActivitybyParam(CVManager.Param.title, "unique");
		Assert.assertEquals(activities.size(), 1);
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#getCV(java.lang.Integer)}.
	 */
	@Test
	public void testGetCV() {
		List<CV> cv = manager.getCV();
		Assert.assertTrue(cv.size() > 0);
	}

	/**
	 * Test method for {@link database.services.proxy.AsAnonymous#getActivity(java.lang.Integer)}.
	 * 
	 */
	@Test
	public void testGetActivity() {
		List<Activity> activities = manager.getActivitybyParam(CVManager.Param.title, "unique");
		Activity Activity = manager.getActivity(activities.get(0).getId());
		Assert.assertTrue(Activity.getTitle().equals("unique"));
	}

}