package pl.coderslab.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy	=	ModernDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ModernDate {

	String	message()	default	"{pl.coderslab.validator.ModernDate.message}"; //tutaj muszą być nawiasy klamrowe
	
	
	Class<?>[]	groups()	default	{};
		
		
	Class<?	extends	Payload>[]	payload()	default	{};
}
