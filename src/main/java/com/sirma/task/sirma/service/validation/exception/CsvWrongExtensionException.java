package com.sirma.task.sirma.service.validation.exception;

/**
 * Exception for wrong extension that means if we upload different file from CSV.
 */
public class CsvWrongExtensionException extends CsvException {

    private static final String WRONG_EXTENSION_MESSAGE_EXCEPTION =
            "The file with name: %s is with wrong extension, please upload a CSV file.";

    /**
     * Constructor.
     *
     * @param fileName the name of the file.
     */
    public CsvWrongExtensionException(String fileName) {
        super(String.format(WRONG_EXTENSION_MESSAGE_EXCEPTION, fileName));
    }
}
