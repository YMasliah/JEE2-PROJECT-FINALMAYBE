package myapp.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import database.beans.Activity;
import database.beans.CV;
import database.services.imp.CVManager.Param;
import database.services.proxy.AsAnonymous;
import database.services.proxy.AsUser;

@ManagedBean(name = "CVController")
@SessionScoped
public class CVController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	AsAnonymous asAnonymous;
	
	@EJB
	AsUser asUser;
	
//	@ManagedProperty("#{LoginController}")
//    private LoginController loginController;
	
	@SuppressWarnings("unused")
	private List<Activity> activities;
	
	private List<CV> cvs;
	
	private Integer idSelected; 
	
	private Activity activity;
	
	@PostConstruct
    public void init() {
		cvs = asUser.getCV();
    }
	
	public void selectedCV(Integer id){
		idSelected = id;
	}
	
	public void refreshCVs() {
		cvs = asUser.getCV();
	}
	
	public List<CV> getCvs() {
		return cvs;
	}

	public void setCvs(List<CV> cvs) {
		this.cvs = cvs;
	}

	public Integer getIdSelected() {
		return idSelected;
	}

	public void setIdSelected(Integer idSelected) {
		this.idSelected = idSelected;
	}

	public List<Activity> getActivities() {
		return asUser.getActivitybyParam(Param.id_cv, idSelected);
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}
