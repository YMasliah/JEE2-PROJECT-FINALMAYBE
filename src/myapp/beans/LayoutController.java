package myapp.beans;

import javax.ejb.EJB;

import database.services.proxy.AsAnonymous;
import database.services.proxy.AsUser;

public class LayoutController {

	@EJB
	AsAnonymous asAnonymous;
	
	@EJB
	AsUser AsUser;
	
}
