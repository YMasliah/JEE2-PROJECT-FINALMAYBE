/**
 * 
 */
package database.services;

import java.util.List;

import database.beans.Person;

/**
 * @author masliah yann
 *
 */
public interface IPersonManager {
	public enum Param {
		id, eMail, name, surname, webAdress, birthDate, password;
	}

	/**
	 * envoi une exception si le parametre n'as pas le droit d'etre modifier
	 * 
	 * @param param
	 * @param value
	 * 			@RolesAllowed({ "User" })
	 */
	public void editParam(Person person, Param param, Object value);

	/**
	 * recupere le parametre souhaiter
	 * 
	 * @param param
	 * @return la valeur sous forme de String @RolesAllowed({ "User" })
	 */
	public String getParam(Person person, Param param);

	/**
	 * recupere la totalit�es des informations de la perssonne
	 * 
	 * @return @RolesAllowed({ "User" })
	 */
	public Person getParams(Person person);

	/**
	 * redefinie toute les valeurs de la personne, sauf la cl� primaire.
	 * 
	 * @param person
	 * 			@RolesAllowed({ "User" })
	 */
	public void setParams(Person personBefore, Person personAfter);

	/**
	 * supprime la personne et se deconnecte @RolesAllowed({ "User" })
	 */
	public void deletePerson(Person person);

	/**
	 * recherche d'une personne avec un parametre unique
	 * 
	 * @param param
	 * @param value
	 * @return une partie des donn�es seront filtr�e avant d'etre retourner.
	 * @PermitAll
	 */
	public List<Person> getPersonByParam(Param param, Object value);

	/**
	 * cree un nouvel utilisateur
	 * 
	 * @param email
	 * @param name
	 * @param surname
	 * @param password
	 * @PermitAll
	 */
	public void newPerson(String email, String name, String surname, String password);
}