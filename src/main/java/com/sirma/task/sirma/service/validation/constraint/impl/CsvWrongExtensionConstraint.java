package com.sirma.task.sirma.service.validation.constraint.impl;

import com.sirma.task.sirma.service.validation.constraint.Constraint;
import com.sirma.task.sirma.service.validation.exception.CsvWrongExtensionException;
import org.springframework.web.multipart.MultipartFile;

/**
 * This is a constraint that checks if the file extension is not CSV and throws an exception.
 */
public class CsvWrongExtensionConstraint implements Constraint {

    private static final String CSV_EXTENSION = "text/csv";

    private final MultipartFile csvFile;

    /**
     * Constructor.
     *
     * @param csvFile {@link MultipartFile}.
     */
    public CsvWrongExtensionConstraint(MultipartFile csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public void validate() {

        String fileType = csvFile.getContentType();

        boolean isItCsv = checkIfFileIsCsvExtension(fileType);

        if (!isItCsv) {
            String fileName = csvFile.getOriginalFilename();
            throw new CsvWrongExtensionException(fileName);
        }
    }

    private boolean checkIfFileIsCsvExtension(String fileType) {
        return CSV_EXTENSION.equals(fileType);
    }
}
