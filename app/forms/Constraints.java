package forms;

import global.Sesame;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;

import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;

import play.data.validation.Constraints.Validator;

public class Constraints {
	/**
     * Defines a loginNotAlreadyUsed constraint
     */
    @Target({FIELD})
    @Retention(RUNTIME)
    @Constraint(validatedBy = LoginNotAlreadyUsedValidator.class)
    @play.data.Form.Display(name="constraint.loginNotAlreadyUsed", attributes={})
    public static @interface LoginNotAlreadyUsed {
        String message() default LoginNotAlreadyUsedValidator.message;
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
    
    /**
     * Validator for <code>@LoginNotAlreadyUsed</code> fields.
     */
    public static class LoginNotAlreadyUsedValidator extends Validator<String> implements ConstraintValidator<LoginNotAlreadyUsed, String> {
        
        final static public String message = "error.loginNotAlreadyUsed";
        
        public LoginNotAlreadyUsedValidator() {}
        
        @Override
        public void initialize(LoginNotAlreadyUsed constraintAnnotation) {
        }
        
        @Override
        public boolean isValid(String login) {
        	ObjectConnection oc = Sesame.getObjectConnection();

        	try {
				String uid = login.toLowerCase();
				System.out.println(uid);
				
				Object u = oc.getObject(models.User.URI + uid);
				System.out.println(u instanceof models.User);

				return !(oc.getObject(models.User.URI + uid) instanceof models.User);
			} catch (RepositoryException e) {
				return false;
			}
			
        }
    }
    
    /**
     * Defines a TagWellFormed constraint
     */
    @Target({FIELD})
    @Retention(RUNTIME)
    @Constraint(validatedBy = TagWellFormedValidator.class)
    @play.data.Form.Display(name="constraint.loginNotAlreadyUsed", attributes={})
    public static @interface TagWellFormed {
        String message() default TagWellFormedValidator.message;
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
    
    /**
     * Validator for <code>@LoginNotAlreadyUsed</code> fields.
     */
    public static class TagWellFormedValidator extends Validator<String> implements ConstraintValidator<TagWellFormed, String> {
        
        final static public String message = "error.tags";
        
        public TagWellFormedValidator() {}
        
        @Override
        public void initialize(TagWellFormed constraintAnnotation) {
        }
        
        @Override
        public boolean isValid(String tags) {
        	if (tags.trim().equals("")) {
        		return true;
        	}
        	
        	for (String nameTag : tags.split(",")) {
        		nameTag = nameTag.trim();
    			if (!nameTag.matches("\\w+") || nameTag.length() > 15) {
    				return false;
    			}
    		}
        	
        	return true;
        }
    }
    
    /**
     * Defines a True constraint
     */
    @Target({FIELD, METHOD})
    @Retention(RUNTIME)
    @Constraint(validatedBy = TrueValidator.class)
    @play.data.Form.Display(name="constraint.LoginExists", attributes={})
    public static @interface True {
        String message() default TrueValidator.message;
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
    
    /**
     * Validator for <code>@LoginExists</code> fields.
     */
    public static class TrueValidator extends Validator<Boolean> implements ConstraintValidator<True, Boolean> {
        
        final static public String message = "error.true";
        
        public TrueValidator() {}
        
        @Override
        public void initialize(True constraintAnnotation) {
        }
        
        @Override
        public boolean isValid(Boolean bool) {
        	return bool;	
        }
    }
}
