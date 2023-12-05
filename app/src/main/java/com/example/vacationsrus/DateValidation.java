package com.example.vacationsrus;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.apache.commons.validator.GenericValidator;

public class DateValidation implements DateValidator {
    @Override
    public boolean isValid(String dateStr) {
        return GenericValidator.isDate(dateStr, "yyyy-MM-dd", true);
    }
}
