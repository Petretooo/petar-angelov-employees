package com.sirma.task.sirma.service;

import com.sirma.task.sirma.service.dto.response.EmployeeMatchResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Employee service.
 */
public interface EmployeeService {

    /**
     * Method that accepts CSV file and return the employees that worked together on the same project.
     *
     * @param file {@link MultipartFile} this file represents CSV file.
     * @return a {@link List} that contains {@link EmployeeMatchResponse} who worked on same project.
     */
    List<EmployeeMatchResponse> findEmployeesInSameProject(MultipartFile file);
}
