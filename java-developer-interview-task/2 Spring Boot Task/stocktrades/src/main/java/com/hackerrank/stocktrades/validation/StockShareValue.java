package com.hackerrank.stocktrades.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = StockShareValueValidator.class)
@Documented
public @interface StockShareValue {

    String message() default "Stock share value is out of accepted range [1, 100]";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
