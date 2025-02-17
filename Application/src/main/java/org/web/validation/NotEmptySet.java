package org.web.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {NotEmptySetValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptySet {
    String message() default "El conjunto de roles no puede estar vacío";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}