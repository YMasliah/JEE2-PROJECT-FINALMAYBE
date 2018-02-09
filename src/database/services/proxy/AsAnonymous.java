/**
 * 
 */
package database.services.proxy;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import database.beans.Activity;
import database.beans.CV;
import database.beans.Kind;
import database.beans.Person;
import database.services.imp.CVManager;
import database.services.imp.CVManager.Param;
import database.services.imp.PersonManager;

/**
 * @author masliah yann
 *
 */
@Stateless
@Startup
public class AsAnonymous{

	@EJB
	private PersonManager personManager;
	@EJB
	private CVManager cvManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#getCV(database.beans.Person)
	 */
	@PermitAll
	public CV getCV(Person person) {
		return cvManager.getCV(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#addActivity(database.beans.Person,
	 * java.lang.Integer, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@PermitAll
	public void addActivity(Person person, Integer year, Kind kind, String title, String description,
			String webSite) {
		cvManager.addActivity(person, year, kind, title, description, webSite);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#addActivity(database.beans.Person,
	 * database.beans.Activity)
	 */
	@PermitAll
	public void addActivity(Person person, Activity activity) {
		cvManager.addActivity(person, activity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#updateActivity(database.beans.Person,
	 * java.lang.Integer, database.services.ICVManager.Param, java.lang.Object)
	 */
	@PermitAll
	public boolean updateActivityParam(Person person, Integer idAct, Param param, Object value) {
		return cvManager.updateActivityParam(person, idAct, param, value);
	}

	@PermitAll
	public boolean editActivity(Person person, Activity activity) {
		return cvManager.editActivity(person, activity);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#deleteActivity(database.beans.Person,
	 * java.lang.Integer)
	 */
	@PermitAll
	public boolean deleteActivity(Person person, Integer idAct) {
		return cvManager.deleteActivity(person, idAct);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#getCVbyPerson(java.lang.Integer)
	 */
	@PermitAll
	public CV getCVbyPersonId(Integer id) {
		return cvManager.getCVbyPersonId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#getActivitybyParam(database.services.
	 * ICVManager. Param, java.lang.Object)
	 */
	@PermitAll
	public List<Activity> getActivitybyParam(Param param, Object value) {
		return cvManager.getActivitybyParam(param, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#getCV(java.lang.Integer)
	 */
	@PermitAll
	public List<CV> getCV() {
		return cvManager.getCV();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#getActivity(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@PermitAll
	public Activity getActivity(Integer ID_ACT) {
		return cvManager.getActivity(ID_ACT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ILoginManager#login(java.lang.String,
	 * java.lang.String)
	 */
	@PermitAll
	public Person login(String eMail, String password) {
		return personManager.login(eMail, password);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.IPersonManager#editParam(database.beans.Person,
	 * database.services.IPersonManager.Param, java.lang.Object)
	 */
	@PermitAll
	public void editParam(Person person, PersonManager.Param param, Object value) {
		personManager.editParam(person, param, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.IPersonManager#getParam(database.beans.Person,
	 * database.services.IPersonManager.Param)
	 */
	@PermitAll
	public String getParam(Person person, PersonManager.Param param) {
		return personManager.getParam(person, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.IPersonManager#getParams(database.beans.Person)
	 */
	@PermitAll
	public Person getParams(Person person) {
		return personManager.getParams(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.IPersonManager#setParams(database.beans.Person,
	 * database.beans.Person)
	 */
	/**
	 * comparaisons a faire
	 */
	@PermitAll
	public void setParams(Person personBefore, Person personAfter) {
		personManager.setParams(personBefore, personAfter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.IPersonManager#deletePerson(database.beans.Person)
	 */
	@PermitAll
	public void deletePerson(Person person) {
		personManager.deletePerson(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.IPersonManager#getPersonByParam(database.services.
	 * IPersonManager.Param, java.lang.Object)
	 */
	@PermitAll
	public List<Person> getPersonByParam(PersonManager.Param param, Object value) {
		return personManager.getPersonByParam(param, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.IPersonManager#newPerson(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@PermitAll
	public void newPerson(String eMail, String name, String surname, String password) {
		personManager.newPerson(eMail, name, surname, password);
	}
}
