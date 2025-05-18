package com.sirma.task.sirma.service.impl;

import com.sirma.task.sirma.service.EmployeeService;
import com.sirma.task.sirma.service.dto.response.EmployeeMatchResponse;
import com.sirma.task.sirma.model.Employee;
import com.sirma.task.sirma.service.validation.validator.Validator;
import com.sirma.task.sirma.service.validation.validator.impl.CsvValidatorImpl;
import com.sirma.task.sirma.utility.helper.CsvHelper;
import com.sirma.task.sirma.utility.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * This is an implementation of {@link EmployeeService}.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final CsvHelper csvHelper;

    /**
     * Constructor.
     *
     * @param employeeMapper {@link EmployeeMapper}.
     * @param csvHelper      {@link CsvHelper}.
     */
    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper, CsvHelper csvHelper) {
        this.employeeMapper = employeeMapper;
        this.csvHelper = csvHelper;
    }

    @Override
    public List<EmployeeMatchResponse> findEmployeesInSameProject(MultipartFile file) {

        Validator validator = new CsvValidatorImpl(file);
        validator.validate();

        List<Employee> employees;

        try {
            employees = csvHelper.convertCsvToEmployee(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (employees == null || employees.isEmpty()) {
            return Collections.emptyList();
        }

        return employeeMapper.mapEmployeeListToEmployeeMatchResponseList(employees);
    }
}
