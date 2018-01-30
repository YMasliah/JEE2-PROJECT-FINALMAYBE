/**
 * 
 */
package database.services.imp;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import database.beans.Person;

/**
 * @author masliah yann
 *
 */
@Stateful
public class LoginManager{

	@PersistenceContext(unitName = "myMySQLBase")
    EntityManager em;
	
	/* (non-Javadoc)
	 * @see database.services.ILoginManager#login(java.lang.String, java.lang.String)
	 */
	@PermitAll
	public Person login(String eMail, String password) {
		Person person = em.find(Person.class, eMail);
		if(person.getPassword().equals(password)) {
			return person;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see database.services.ILoginManager#logout()
	 */
	@RolesAllowed("User")
	public Person logout(Person person) {
		return em.find(Person.class, "raz");
	}
}
