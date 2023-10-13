package store.lib;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import org.springframework.beans.BeanWrapperImpl;
import store.dto.request.UserRegistrationRequestDto;

public class RepeatPasswordValidator
        implements ConstraintValidator<RepeatPasswordMatch, UserRegistrationRequestDto> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(RepeatPasswordMatch constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.repeatPassword = constraintAnnotation.repeatPassword();
    }

    @Override
    public boolean isValid(UserRegistrationRequestDto value, ConstraintValidatorContext context) {
        Object pass = new BeanWrapperImpl(value).getPropertyValue(password);
        Object repeatPass = new BeanWrapperImpl(value).getPropertyValue(repeatPassword);
        return Objects.equals(pass, repeatPass);
    }
}
