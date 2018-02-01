/**
 * 
 */
package myapp.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import database.beans.Person;

/**
 * @author masliah yann
 *
 */
@ManagedBean(name="person")
@SessionScoped
public class LoginManager extends LayoutController{
	
	@Inject
	Person person;
//	String eMail, String password
	public void login() {
		System.out.println("rip");
		person = asAnonymous.login("unique", "vasavoir");
		System.out.println(person);
	}
	
	public void logout() {
		person = null;
	}
	
	public String getPerson(){
		System.out.println(person);
		return person.toString();
	}
	
}
