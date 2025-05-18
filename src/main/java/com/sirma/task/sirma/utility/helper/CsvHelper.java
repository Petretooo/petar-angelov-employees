package com.sirma.task.sirma.utility.helper;

import com.sirma.task.sirma.model.Employee;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * This is a CSV helper for converting the CSV file data in readable and ready for manipulation data.
 */
@Service
public class CsvHelper {

    private static final String EMPLOYEE_ID = "EmpID";
    private static final String PROJECT_ID = "ProjectID";
    private static final String DATE_FROM = "DateFrom";
    private static final String DATE_TO = "DateTo";
    private static final String NULL = "NULL";
    private static final String CONVERTING_CSX_FILE_FAIL_MESSAGE = "Converting CSX file failed";
    private static final String UNSUPPORTED_DATE_FORMAT_MESSAGE = "Unsupported date format: ";

    public static final String YYYY_MM_DD_DASHES = "yyyy-MM-dd";
    public static final String DD_MM_YYYY_DASHES = "dd-MM-yyyy";
    public static final String MM_DD_YYYY_SLASHES = "MM/dd/yyyy";
    public static final String DD_MM_YYYY_SLASHES = "dd/MM/yyyy";
    public static final String MM_D_YYYY_SLASHES = "MM/d/yyyy";
    public static final String M_D_YYYY_SLASHES = "M/d/yyyy";

    private static final DateTimeFormatter[] FORMATTERS = {
            DateTimeFormatter.ofPattern(YYYY_MM_DD_DASHES),
            DateTimeFormatter.ofPattern(DD_MM_YYYY_DASHES),
            DateTimeFormatter.ofPattern(MM_DD_YYYY_SLASHES),
            DateTimeFormatter.ofPattern(DD_MM_YYYY_SLASHES),
            DateTimeFormatter.ofPattern(MM_D_YYYY_SLASHES),
            DateTimeFormatter.ofPattern(M_D_YYYY_SLASHES),
    };

    /**
     * This method takes the date from the CSV file and populate it in a {@link List} of {@link Employee}.
     *
     * @param file {@link MultipartFile}.
     * @return a {@link List} of {@link Employee}.
     * @throws IOException if converting file's data fail.
     */
    public List<Employee> convertCsvToEmployee(MultipartFile file) throws IOException {

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .setIgnoreHeaderCase(true)
                    .setTrim(true)
                    .build();

            CSVParser csvParser = CSVParser.parse(reader, format);

            return StreamSupport.stream(csvParser.spliterator(), false)
                    .map(this::getEmployee)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new IOException(CONVERTING_CSX_FILE_FAIL_MESSAGE + e.getMessage(), e);
        }
    }

    private Employee getEmployee(CSVRecord csvRecord) {

        Integer employeeId = Integer.parseInt(csvRecord.get(EMPLOYEE_ID));
        Integer projectId = Integer.parseInt(csvRecord.get(PROJECT_ID));
        LocalDate dateFrom = parseDate(csvRecord.get(DATE_FROM));
        LocalDate dateTo = csvRecord.get(DATE_TO).equalsIgnoreCase(NULL)
                ? LocalDate.now()
                : parseDate(csvRecord.get(DATE_TO));

        return new Employee(employeeId, projectId, dateFrom, dateTo);
    }

    private LocalDate parseDate(String dateStr) {

        for (DateTimeFormatter fmt : FORMATTERS) {
            try {
                return LocalDate.parse(dateStr.trim(), fmt);
            } catch (DateTimeParseException ignored) {
            }
        }

        throw new IllegalArgumentException(UNSUPPORTED_DATE_FORMAT_MESSAGE + dateStr);
    }
}
