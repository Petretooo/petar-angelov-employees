package com.sirma.task.sirma.api;

import com.sirma.task.sirma.service.dto.response.EmployeeMatchResponse;
import com.sirma.task.sirma.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Rest controller for employees.
 */
@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin
public class EmployeeApi {

    private final EmployeeService employeeService;

    /**
     * Constructor
     *
     * @param employeeService {@link EmployeeService}.
     */
    @Autowired
    public EmployeeApi(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * POST web service for uploading CSV files.
     *
     * @param file {@link MultipartFile} represents CSV file.
     * @return a response of {@link List} that contains {@link EmployeeMatchResponse} who worked on same project.
     */
    @PostMapping("/upload")
    public ResponseEntity<List<EmployeeMatchResponse>> uploadFile(@RequestParam("file") MultipartFile file) {

        List<EmployeeMatchResponse> employeeMatchResponses = employeeService.findEmployeesInSameProject(file);

        return ResponseEntity.ok(employeeMatchResponses);
    }

}
