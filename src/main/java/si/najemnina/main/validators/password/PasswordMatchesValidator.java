package si.najemnina.main.validators.password;

import si.najemnina.main.auth.dto.UserLoginDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
  implements ConstraintValidator<PasswordMatches, Object> {
    
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {       
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserLoginDTO user = (UserLoginDTO) obj;
        return user.password.equals(user.password);
    }     
}