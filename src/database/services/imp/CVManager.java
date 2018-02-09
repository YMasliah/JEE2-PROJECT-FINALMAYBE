/**
 * 
 */
package database.services.imp;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import database.beans.Activity;
import database.beans.CV;
import database.beans.Kind;
import database.beans.Person;

/**
 * @author masliah yann
 *
 */
@Stateless
@Startup
public class CVManager{

	public enum Param {
		id_cv,
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
	public void addActivity(Person person, Integer year, Kind kind, String title, String description,
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
		CV cv = em.find(CV.class, person.getCv().getId());
		cv.addActivity(activity);
		activity.setCv(cv);
		em.merge(person);
		em.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#updateActivity(database.beans.Person,
	 * java.lang.Integer, database.services.ICVManager.Param, java.lang.Object)
	 */
	@RolesAllowed({ "User" })
	public boolean updateActivityParam(Person person, Integer idAct, Param param, Object value) {
		Activity activity = em.find(Activity.class, idAct);
		switch (param) {
		case description:
			activity.setDescription((String) value);
			break;
		case kind:
			activity.setKind((Kind) value);
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
	 * @see database.services.ICVManager#updateActivity(database.beans.Person,
	 * java.lang.Integer, database.services.ICVManager.Param, java.lang.Object)
	 */
	@RolesAllowed({ "User" })
	public boolean editActivity(Person person, Activity activity) {
		if(em.find(Activity.class, activity.getId()) != null){
			em.merge(activity);
			return true;
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see database.services.ICVManager#deleteActivity(database.beans.Person,
	 * java.lang.Integer)
	 */
	@RolesAllowed({ "User" })
	public boolean deleteActivity(Person person, Integer idAct) {
		CV cv = em.find(CV.class, person.getCv().getId());
		Activity activity = em.find(Activity.class, idAct);
		int tempId = cv.getActivities().indexOf(activity);
		cv.getActivities().remove(tempId);
		em.remove(activity);
		em.flush();
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
		case id_cv:
			return ((CV)em.find(CV.class, value)).getActivities();
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
