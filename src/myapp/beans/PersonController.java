package myapp.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import database.beans.Person;
import database.services.proxy.AsAnonymous;
import database.services.proxy.AsUser;

@ManagedBean(name = "PersonController")
@ViewScoped
public class PersonController {

	@EJB
	AsAnonymous asAnonymous;
	
	@EJB
	AsUser asUser;
	
	Person person;
	
    public void init(Person person) {
		if(person == null){
			this.person = new Person();
		}
		else{
			this.person = person;
		}
    }
	
	public void addPerson(){
		asUser.newPerson(person.geteMail(), person.getName(), person.getSurname(), person.getPassword());
	}
	
	public void editPerson(){
		asUser.editPerson(person);
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}
