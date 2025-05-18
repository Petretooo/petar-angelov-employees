package com.sirma.task.sirma.service.validation.exception;

/**
 * Exception when the CSV file is with empty name.
 */
public class CsvEmptyNameException extends CsvException {

    private static final String EMPTY_NAME_MESSAGE_EXCEPTION ="CSV file has no name, please provide name.";

    /**
     * Constructor.
     */
    public CsvEmptyNameException() {
        super(EMPTY_NAME_MESSAGE_EXCEPTION);
    }
}
