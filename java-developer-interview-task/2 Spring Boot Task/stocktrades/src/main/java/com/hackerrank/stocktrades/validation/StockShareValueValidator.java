package com.hackerrank.stocktrades.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StockShareValueValidator implements ConstraintValidator<StockShareValue, Integer> {
    Integer minimum = 1;
    Integer maximum = 100;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value >= minimum && value <= maximum;
    }

}
