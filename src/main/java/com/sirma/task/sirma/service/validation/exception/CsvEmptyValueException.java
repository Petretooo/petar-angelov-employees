package com.sirma.task.sirma.service.validation.exception;

/**
 * Exception when the CSV file is empty.
 */
public class CsvEmptyValueException extends CsvException {

    private static final String EMPTY_VALUE_MESSAGE_EXCEPTION ="CSV file with name: %s is empty";

    /**
     * Constructor.
     *
     * @param fileName the name of the file.
     */
    public CsvEmptyValueException(String fileName) {
        super(String.format(EMPTY_VALUE_MESSAGE_EXCEPTION, fileName));
    }
}
