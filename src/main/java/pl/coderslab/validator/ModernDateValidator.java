package pl.coderslab.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ModernDateValidator implements ConstraintValidator<ModernDate, Integer>{

	@Override
	public void initialize(ModernDate constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {

		return value>1950;		// tu jest nasz warunek
	}

}
