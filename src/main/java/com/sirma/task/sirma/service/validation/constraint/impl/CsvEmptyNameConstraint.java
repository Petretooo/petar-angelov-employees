package com.sirma.task.sirma.service.validation.constraint.impl;

import com.sirma.task.sirma.service.validation.constraint.Constraint;
import com.sirma.task.sirma.service.validation.exception.CsvEmptyNameException;
import com.sirma.task.sirma.service.validation.exception.CsvEmptyValueException;
import org.springframework.web.multipart.MultipartFile;

/**
 * This is a constraint that checks if the file's name is empty and throws an exception.
 */
public class CsvEmptyNameConstraint implements Constraint {

    private static final String REGEX_SPLIT_FOR_DOT = "\\.";
    private static final int FIRST_ELEMENT_INDEX = 0;

    private final MultipartFile csvFile;

    /**
     * Constructor.
     *
     * @param csvFile {@link MultipartFile}.
     */
    public CsvEmptyNameConstraint(MultipartFile csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public void validate() {

        String fileName = csvFile.getOriginalFilename();

        boolean isEmpty = checkIfEmpty(fileName);

        if (isEmpty) {
            throw new CsvEmptyNameException();
        }
    }

    private boolean checkIfEmpty(String fileName) {

        String[] words = fileName.split(REGEX_SPLIT_FOR_DOT);
        String fileNameWithoutExtension = words[FIRST_ELEMENT_INDEX];

        return fileNameWithoutExtension.trim().isEmpty();
    }
}
