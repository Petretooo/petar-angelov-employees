package com.sirma.task.sirma.service.validation.validator.impl;

import com.sirma.task.sirma.service.validation.constraint.Constraint;
import com.sirma.task.sirma.service.validation.constraint.impl.CsvEmptyValueConstraint;
import com.sirma.task.sirma.service.validation.constraint.impl.CsvEmptyNameConstraint;
import com.sirma.task.sirma.service.validation.constraint.impl.CsvWrongExtensionConstraint;
import com.sirma.task.sirma.service.validation.validator.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Validator for CSV files.
 */
public class CsvValidatorImpl implements Validator {

    private final List<Constraint> constraints;

    /**
     * Constructor.
     *
     * @param csvFile a {@link MultipartFile}.
     */
    public CsvValidatorImpl(MultipartFile csvFile) {
        this.constraints = new ArrayList<>();
        addConstraint(csvFile);
    }

    @Override
    public void validate() {
        for (Constraint constraint : constraints) {
            constraint.validate();
        }
    }

    private void addConstraint(MultipartFile csvFile) {
        this.constraints.add(new CsvEmptyNameConstraint(csvFile));
        this.constraints.add(new CsvEmptyValueConstraint(csvFile));
        this.constraints.add(new CsvWrongExtensionConstraint(csvFile));
    }
}
