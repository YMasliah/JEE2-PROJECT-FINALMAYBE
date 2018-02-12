/**
 * 
 */
package database.services;

import java.util.List;

import database.beans.Activity;
import database.beans.CV;
import database.beans.Person;

/**
 * @author masliah yann
 *
 * la totalitee des fonctionnalitees sont utilisable grace a un utilisateur authentifier
 */
public interface ICVManager {
	public enum Param {
		year,
		kind,
		title,
		description,
		webSite;	
		}
		
	/**
	 * recupere un CV
	 * @param NumCV
	 * @return
	 * 	@RolesAllowed({ "User" })
	 */
	public CV getCV(Person person);
	
	/**
	 * version 1
	 * ajoute une nouvelle activitee dans le cv fournis en parametre.
	 * return le numero de l'activitee
	 * 	@RolesAllowed({ "User" })
	 */
	public void addActivity(Person person, Integer year, String kind, String title, String description, String webSite);
	
	/**
	 * version 2
	 * ajoute une nouvelle activitee dans le cv fournis en parametre.
	 * return le numero de l'activitee
	 * 	@RolesAllowed({ "User" })
	 */
	public void addActivity(Person person, Activity activity);
	
	/**
	 * modifie un champ de l'activitee du CV
	 * @param Num
	 * @return
	 * 	@RolesAllowed({ "User" })
	 */
	public boolean updateActivity(Person person, Integer NumACT, Param param, Object Value);
	
	/**
	 * supprime l'activitee du CV
	 * @param Num
	 * @return
	 * 	@RolesAllowed({ "User" })
	 */
	public boolean deleteActivity(Person person, Integer NumACT);
	
	/**
	 * recupere le CV de la personne voulu
	 * @param id
	 * @return
	 * @PermitAll
	 */
	public CV getCVbyPersonId(Integer id);
	
	/**
	 * recupere les activitees disposant de la valeur souhaitee dans le parametre voulu.
	 * @param param
	 * @param value
	 * @return
	 * @PermitAll
	 */
	public List<Activity> getActivitybyParam(Param param, Object value);
	
	/**
	 * recupere tout les CV
	 * @param NumCV
	 * @return
	 * @PermitAll
	 */
	public List<CV> getCV();
	
	/**
	 * recupere les informations d'une activitee d'un CV
	 * @param ID_CV
	 * @param ID_ACT
	 * @return
	 * @PermitAll
	 */
	public Activity getActivity(Integer ID_ACT);
}