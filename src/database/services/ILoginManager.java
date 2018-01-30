/**
 * 
 */
package database.services;

import database.beans.Person;

/**
 * @author masliah yann
 *
 */
public interface ILoginManager{

	/**
	 * login la personne et passe au status de user
	 * @param name
	 * @param password
	 * @return 
	 * 	@PermitAll
	 */
	public Person login(String eMail, String password);
	
	/**
	 * logout la personne
	 * 	@RolesAllowed("User")
	 */
	public Person logout(Person person);
}
