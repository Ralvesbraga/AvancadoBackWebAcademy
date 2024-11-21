package br.ufac.sgcmapi.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Payload;

import jakarta.validation.Constraint;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HorarioAtendimentoValidator.class)
public @interface HorarioAtendimento {
    String message() default "fora do horario de atendimento (14:00 - 20:00)";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
