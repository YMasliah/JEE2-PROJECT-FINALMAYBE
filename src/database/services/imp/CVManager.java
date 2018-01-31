/**
 * 
 */
package database.services.imp;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import database.beans.Activity;
import database.beans.CV;
import database.beans.Person;

/**
 * @author masliah yann
 *
 */
@Stateful
public class CVManager{

	public enum Param {
		year,
		kind,
		title,
		description,
		webSite;	
		}
	
	@PersistenceContext(unitName = "myMySQLBase")
	EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#getCV(database.beans.Person)
	 */
	@RolesAllowed({ "User" })
	public CV getCV(Person person) {
		return em.find(CV.class, person.getCv().getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#addActivity(database.beans.Person,
	 * java.lang.Integer, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@RolesAllowed({ "User" })
	public void addActivity(Person person, Integer year, String kind, String title, String description,
			String webSite) {
		Activity temp = new Activity();
		temp.setYear(year);
		temp.setKind(kind);
		temp.setTitle(title);
		temp.setDescription(description);
		temp.setWebSite(webSite);
		CV cv = em.find(CV.class, person.getCv().getId());
		cv.addActivity(temp);
		temp.setCv(cv);
//		em.merge(cv);
//		em.persist(temp);
		em.merge(cv.getPerson());
		em.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#addActivity(database.beans.Person,
	 * database.beans.Activity)
	 */
	@RolesAllowed({ "User" })
	public void addActivity(Person person, Activity activity) {
		em.persist(activity);
		person.getCv().addActivity(activity);
		em.merge(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#updateActivity(database.beans.Person,
	 * java.lang.Integer, database.services.ICVManager.Param, java.lang.Object)
	 */
	@RolesAllowed({ "User" })
	public boolean updateActivity(Person person, Integer idAct, Param param, Object value) {
		int temp = person.getCv().getActivities().indexOf(idAct);
		Activity activity = person.getCv().getActivities().get(temp);
		switch (param) {
		case description:
			activity.setDescription((String) value);
			break;
		case kind:
			activity.setKind((String) value);
			break;
		case title:
			activity.setTitle((String) value);
			break;
		case webSite:
			activity.setWebSite((String) value);
			break;
		case year:
			activity.setYear((Integer) value);
			break;
		default:
			return false;
		}
		em.merge(activity);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#deleteActivity(database.beans.Person,
	 * java.lang.Integer)
	 */
	@RolesAllowed({ "User" })
	public boolean deleteActivity(Person person, Integer idAct) {
		person.getCv().getActivities().remove(idAct);
		em.merge(person);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#getCVbyPerson(java.lang.Integer)
	 */
	@PermitAll
	public CV getCVbyPersonId(Integer id) {
		return em.find(CV.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * database.services.ICVManager#getActivitybyParam(database.services.ICVManager.
	 * Param, java.lang.Object)
	 */
	@PermitAll
	public List<Activity> getActivitybyParam(Param param, Object value){
		switch (param){
		case description:
			return em.createQuery("select a from Activities a where a.description = :value", Activity.class)
					.setParameter("value", value).getResultList();
		case kind:
			return em.createQuery("select a from Activities a where a.kind = :value", Activity.class)
					.setParameter("value", value).getResultList();
		case title:
			return em.createQuery("select a from Activities a where a.title = :value", Activity.class)
					.setParameter("value", value).getResultList();
		case webSite:
			return em.createQuery("select a from Activities a where a.webSite = :value", Activity.class)
					.setParameter("value", value).getResultList();
		case year:
			return em.createQuery("select a from Activities a where a.year = :value", Activity.class)
					.setParameter("value", value).getResultList();
		default:
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#getCV(java.lang.Integer)
	 */
	@PermitAll
	public List<CV> getCV() {
		return em.createQuery("select c from CVs c", CV.class).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#getActivity(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@PermitAll
	public Activity getActivity(Integer ID_ACT) {
		return em.find(Activity.class, ID_ACT);
	}

}