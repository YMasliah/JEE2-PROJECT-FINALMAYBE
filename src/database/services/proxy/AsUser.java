/**
 * 
 */
package database.services.proxy;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import database.beans.Person;
import database.services.imp.PersonManager;

/**
 * @author masliah yann
 *
 */
@Stateless
@RunAs("User")
public class AsUser{

	@EJB
	private PersonManager personManager;

	/* (non-Javadoc)
	 * @see database.services.IPersonManager#newPerson(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@PermitAll
	public void newPerson(String eMail, String name, String surname, String password) {
		personManager.newPerson(eMail, name, surname, password);
	}
	
	@PermitAll
	public void newPerson(Person person) {
		personManager.newPerson(person);
	}



}
