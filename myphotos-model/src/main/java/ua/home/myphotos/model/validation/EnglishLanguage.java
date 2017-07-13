package ua.home.myphotos.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by vov on 13.07.2017.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = {EnglishLanguageConstraintValidator.class})
@Documented
public @interface EnglishLanguage {
    String message() default "{javax.validation.constraints.Email.message}";

    //0123456789
    boolean withNumbers() default true;

    //.,?!-:()'"[]{}; \t\n
    boolean withPunctuations() default true;

    //~#$%^&*-+=_\\|/@`!'\";:><,.?{}
    boolean withSpecialSymbols() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
