package com.sirma.task.sirma.service;

import com.sirma.task.sirma.service.dto.response.EmployeeMatchResponse;
import com.sirma.task.sirma.service.impl.EmployeeServiceImpl;
import com.sirma.task.sirma.service.validation.exception.CsvEmptyNameException;
import com.sirma.task.sirma.service.validation.exception.CsvEmptyValueException;
import com.sirma.task.sirma.utility.helper.CsvHelper;
import com.sirma.task.sirma.utility.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This is test class for {@link EmployeeService}.
 */
public class EmployeeServiceTest {

    @Test
    void shouldFindEmployeesInTheSameProject() throws Exception {

        EmployeeMapper mapper = new EmployeeMapper();
        CsvHelper csvHelper = new CsvHelper();

        EmployeeService employeeService = new EmployeeServiceImpl(mapper, csvHelper);

        ClassPathResource resource = new ClassPathResource("employees.csv");

        MockMultipartFile file = new MockMultipartFile(
                "file",
                resource.getFilename(),
                "text/csv",
                resource.getInputStream()
        );

        List<EmployeeMatchResponse> responseList = employeeService.findEmployeesInSameProject(file);

        assertNotNull(responseList);
        assertFalse(responseList.isEmpty());
    }

    @Test
    void shouldFailForEmptyValue() throws Exception {

        EmployeeMapper mapper = new EmployeeMapper();
        CsvHelper csvHelper = new CsvHelper();

        EmployeeService employeeService = new EmployeeServiceImpl(mapper, csvHelper);

        ClassPathResource resource = new ClassPathResource("empty.csv");

        MockMultipartFile file = new MockMultipartFile(
                "file",
                resource.getFilename(),
                "text/csv",
                resource.getInputStream()
        );

        assertThrows(CsvEmptyValueException.class, () -> employeeService.findEmployeesInSameProject(file));
    }

    @Test
    void shouldFailForEmptyFileName() throws Exception {

        EmployeeMapper mapper = new EmployeeMapper();
        CsvHelper csvHelper = new CsvHelper();

        EmployeeService employeeService = new EmployeeServiceImpl(mapper, csvHelper);

        ClassPathResource resource = new ClassPathResource(".csv");

        MockMultipartFile file = new MockMultipartFile(
                "file",
                resource.getFilename(),
                "text/csv",
                resource.getInputStream()
        );

        assertThrows(CsvEmptyNameException.class, () -> employeeService.findEmployeesInSameProject(file));
    }
}
