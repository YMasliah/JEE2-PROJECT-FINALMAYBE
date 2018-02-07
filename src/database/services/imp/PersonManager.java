/**
 * 
 */
package database.services.imp;

import java.sql.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import database.beans.CV;
import database.beans.Person;

/**
 * @author masliah yann
 *
 */
@Stateless
@Startup
public class PersonManager{
	
	public enum Param {
		id, eMail, name, surname, webAdress, birthDate, password;
	}
	
	@PersistenceContext(unitName = "myMySQLBase")
    EntityManager em;
	
	/* (non-Javadoc)
	 * @see database.services.IPersonManager#editParam(database.beans.Person, database.services.IPersonManager.Param, java.lang.Object)
	 */
	@RolesAllowed({ "User" })
	public void editParam(Person person, Param param, Object value) {
		switch (param) {
		case birthDate:
			person.setBirthDate((Date) value);
			break;
		case name:
			person.setName((String) value);
			break;
		case surname:
			person.setSurname((String) value);
			break;
		case webAdress:
			person.setWebAdress((String) value);
			break;
		default:
			return;
		}
		em.merge(person);
		em.flush();
	}

	/* (non-Javadoc)
	 * @see database.services.IPersonManager#getParam(database.beans.Person, database.services.IPersonManager.Param)
	 */
	@RolesAllowed({ "User" })
	public String getParam(Person person, Param param) {
		String returnValue;
		switch (param) {
		case birthDate:
			returnValue = person.getBirthDate().toString();
			break;
		case eMail:
			returnValue = person.geteMail();
			break;
		case name:
			returnValue = person.getName();
			break;
		case surname:
			returnValue = person.getSurname();
			break;
		case webAdress:
			returnValue = person.getWebAdress();
			break;
		default:
			returnValue = "";
			break;
		
		}
		return returnValue;
	}

	/* (non-Javadoc)
	 * @see database.services.IPersonManager#getParams(database.beans.Person)
	 */
	@RolesAllowed({ "User" })
	public Person getParams(Person person) {
		return em.find(Person.class, person.geteMail());
	}

	/* (non-Javadoc)
	 * @see database.services.IPersonManager#setParams(database.beans.Person, database.beans.Person)
	 */
	/**
	 * comparaisons a faire
	 */
	@RolesAllowed({ "User" })
	public void setParams(Person personBefore, Person personAfter) {
		em.merge(personAfter);
		em.flush();
	}

	/* (non-Javadoc)
	 * @see database.services.IPersonManager#deletePerson(database.beans.Person)
	 */
	@RolesAllowed({ "User" })
	public void deletePerson(Person person) {
		person = em.merge(person);
	    CV cv = em.merge(person.getCv());
	    em.remove(cv);
		em.remove(person);
	    em.flush();
	}

	/* (non-Javadoc)
	 * @see database.services.IPersonManager#getPersonByParam(database.services.IPersonManager.Param, java.lang.Object)
	 */
	@PermitAll
	public List<Person> getPersonByParam(Param param, Object value) {
		switch (param){
		case birthDate:
			return em.createQuery("select p from Persons p where p.birthDate = :value", Person.class)
					.setParameter("value", value).getResultList();
		case eMail:
			return em.createQuery("select p from Persons p where p.eMail = :value", Person.class)
					.setParameter("value", value).getResultList();
		case id:
			return em.createQuery("select p from Persons p where p.id = :value", Person.class)
					.setParameter("value", value).getResultList();
		case name:
			return em.createQuery("select p from Persons p where p.name = :value", Person.class)
					.setParameter("value", value).getResultList();
		case password:
			return em.createQuery("select p from Persons p where p.password = :value", Person.class)
					.setParameter("value", value).getResultList();
		case surname:
			return em.createQuery("select p from Persons p where p.surname = :value", Person.class)
					.setParameter("value", value).getResultList();
		case webAdress:
			return em.createQuery("select p from Persons p where p.webAdress = :value", Person.class)
					.setParameter("value", value).getResultList();
		default:
			return null;
		
		}
	}

	/* (non-Javadoc)
	 * @see database.services.IPersonManager#newPerson(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@PermitAll
	public void newPerson(String eMail, String name, String surname, String password) {
		Person person = new Person();
		person.seteMail(eMail);
		person.setName(name);
		person.setSurname(surname);
		person.setPassword(password);
		em.persist(person.getCv());
		em.persist(person);
		em.flush();
	}
	
	/* (non-Javadoc)
	 * @see database.services.ILoginManager#login(java.lang.String, java.lang.String)
	 */
	@PermitAll
	public Person login(String eMail, String password) {
		Person person = em.find(Person.class, eMail);
		if(person == null){
			return null;
		}
		if(person.getPassword().equals(password)) {
			return person;
		}
		return null;
	}
	
}
