package myapp.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import database.beans.Person;
import database.services.imp.PersonManager.Param;
import database.services.proxy.AsAnonymous;

@ManagedBean(name = "SearchController")
@ViewScoped
public class SearchController {

	@EJB
	AsAnonymous asAnonymous;
	
	String eMail;
	
	public String search(){
		System.out.println("vivant");
		System.out.println(eMail);
		Person person = asAnonymous.getPersonByParam(Param.eMail, eMail).get(0);
		System.out.println("mort");
		System.out.println(person.toString());
		return "CV?faces-redirect=true&id="+person.getCv().getId();
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
}
