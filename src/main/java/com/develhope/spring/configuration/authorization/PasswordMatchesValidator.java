package com.develhope.spring.configuration.authorization;
import com.develhope.spring.configuration.authorization.CustomAnnotation.PasswordMatches;
import com.develhope.spring.users.dtos.UserRegistrationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserRegistrationDTO user = (UserRegistrationDTO) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}