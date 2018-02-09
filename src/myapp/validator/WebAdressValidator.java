package myapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("myconstraints.url")
public class WebAdressValidator implements Validator {

	@Override
	public void validate(FacesContext ct, UIComponent comp, Object obj) throws ValidatorException {

		if(obj.toString().isEmpty())
			return;
		
		String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		Pattern patt = Pattern.compile(regex);
		Matcher m = patt.matcher(obj.toString());
		
		try {
			if (!m.matches()){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "url inccorecte", "url mal formee");
				throw new ValidatorException(msg);
			}
		} catch (RuntimeException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "url inccorecte", "url mal formee");
			throw new ValidatorException(msg);
			
		}

	}

}