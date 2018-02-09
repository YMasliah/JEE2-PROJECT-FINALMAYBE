package myapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("myconstraints.email")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext ct, UIComponent comp, Object obj) throws ValidatorException {
		Pattern patt = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = patt.matcher(obj.toString());
		
		try {
			if (!m.matches()){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "email inccorecte", "email mal formee");
				throw new ValidatorException(msg);
			}
		} catch (RuntimeException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "email inccorecte", "email mal formee");
			throw new ValidatorException(msg);
			
		}

	}

}