package store.lib;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RepeatPasswordValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatPasswordMatch {
    String message() default "Password doesn't match repeat password";

    String password() default "password";

    String repeatPassword() default "repeatPassword";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
