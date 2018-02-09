/**
 * 
 */
package myapp.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import database.beans.Person;
import database.services.proxy.AsAnonymous;

/**
 * @author masliah yann
 *
 */
@ManagedBean(name = "LoginController")
@SessionScoped
public class LoginController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	AsAnonymous asAnonymous;
	
	private Person person;
    
    private String username;
    
    private String password;
	
	public void login(ActionEvent event) {
		if(username.isEmpty() || password.isEmpty()){
			return;
		}
		person = asAnonymous.login(username, password);
		if(person != null){
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Successful",  "Your email : " + username) );
		}else{
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Echec",  "Wrong credential") );
		}
	}
	
	public void logout() {
		username = "";
		password = "";
		person = null;
		
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Logout",  "have a nice day") );
	}
	
	public Person getPerson(){
		return person;
	}
	
    public String getUsername() {
        return username;
    }
	 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}
