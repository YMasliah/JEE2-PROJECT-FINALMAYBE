/**
 * 
 */
package database.services.imp;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import database.beans.CV;
import database.beans.Person;

/**
 * @author masliah yann
 *
 */
@Stateful
public class PersonManager{
	
	@PersistenceContext(unitName = "myMySQLBase")
    EntityManager em;

	/* (non-Javadoc)
	 * @see database.services.IPersonManager#newPerson(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@RolesAllowed({ "User" })
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
	
	@PermitAll
	public void newPerson(Person person) {
		CV cv = new CV();
		em.persist(cv);
		person.setCv(cv);
		em.persist(person);
		em.flush();
	}
	
	@PermitAll
	public Person searchPerson(String eMail) {
		return em.find(Person.class, eMail);
	}
}
