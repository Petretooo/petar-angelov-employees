package com.sirma.task.sirma.service.validation.constraint.impl;

import com.sirma.task.sirma.service.validation.constraint.Constraint;
import com.sirma.task.sirma.service.validation.exception.CsvEmptyValueException;
import org.springframework.web.multipart.MultipartFile;

/**
 * This is a constraint that checks if the file is empty and throws an exception.
 */
public class CsvEmptyValueConstraint implements Constraint {

    private static final long ZERO_FILE_SIZE = 0L;
    private final MultipartFile csvFile;

    /**
     * Constructor.
     *
     * @param csvFile {@link MultipartFile}.
     */
    public CsvEmptyValueConstraint(MultipartFile csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public void validate() {

        long fileSize = csvFile.getSize();

        boolean isEmpty = checkIfEmpty(fileSize);

        if (isEmpty) {
            String fileName = csvFile.getOriginalFilename();
            throw new CsvEmptyValueException(fileName);
        }
    }

    private boolean checkIfEmpty(long fileSize) {
        return fileSize == ZERO_FILE_SIZE;
    }
}
