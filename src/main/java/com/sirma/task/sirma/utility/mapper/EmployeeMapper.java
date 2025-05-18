package com.sirma.task.sirma.utility.mapper;

import com.sirma.task.sirma.service.dto.response.EmployeeMatchResponse;
import com.sirma.task.sirma.model.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is a helper class for finding matches between employees.
 */
@Service
public class EmployeeMapper {

    private static final int INCLUDE_LAST_DAY = 1;

    /**
     * This method takes a list of employees and finds matches between two employees if they worked together
     * in the same project.
     *
     * @param employees is a {@link List} of {@link Employee}.
     * @return a {@link List} of {@link EmployeeMatchResponse}.
     */
    public List<EmployeeMatchResponse> mapEmployeeListToEmployeeMatchResponseList(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getProjectId))
                .entrySet()
                .stream()
                .flatMap(entry -> findEmployeesWorkedTogether(entry.getKey(), entry.getValue()).stream())
                .collect(Collectors.toList());
    }

    private List<EmployeeMatchResponse> findEmployeesWorkedTogether(Integer projectId, List<Employee> employees) {

        return employees.stream()
                .flatMap(currentEmp -> employees.stream()
                        .filter(nextEmp -> currentEmp.getEmployeeId() < nextEmp.getEmployeeId())
                        .filter(nextEmp -> filterEmployeesWorkedTogether(currentEmp, nextEmp))
                        .map(nextEmp -> mapTpEmployeeMatchResponse(projectId, currentEmp, nextEmp))
                ).collect(Collectors.toList());
    }

    private boolean filterEmployeesWorkedTogether(Employee currentEmployee, Employee nextEmployee) {

        LocalDate start = getLaterDate(currentEmployee, nextEmployee);
        LocalDate end = getEarlierDate(currentEmployee, nextEmployee);

        return !start.isAfter(end);
    }

    private EmployeeMatchResponse mapTpEmployeeMatchResponse(
            Integer projectId,
            Employee currentEmployee,
            Employee nextEmployee) {

        LocalDate start = getLaterDate(currentEmployee, nextEmployee);
        LocalDate end = getEarlierDate(currentEmployee, nextEmployee);

        long daysWorked = ChronoUnit.DAYS.between(start, end.plusDays(INCLUDE_LAST_DAY));

        return new EmployeeMatchResponse(
                currentEmployee.getEmployeeId(),
                nextEmployee.getEmployeeId(),
                projectId,
                daysWorked
        );
    }

    private LocalDate getEarlierDate(Employee currentEmployee, Employee nextEmployee) {

        boolean isCurrentEndEarlierThanNext = checkIfCurrentEndEarlierThanNext(currentEmployee, nextEmployee);

        return isCurrentEndEarlierThanNext ? currentEmployee.getDateTo() : nextEmployee.getDateTo();
    }

    private LocalDate getLaterDate(Employee currentEmployee, Employee nextEmployee) {

        boolean isCurrentStartLaterThanNext = checkIfCurrentStartLaterThanNext(currentEmployee, nextEmployee);

        return isCurrentStartLaterThanNext ? currentEmployee.getDateFrom() : nextEmployee.getDateFrom();
    }

    private boolean checkIfCurrentEndEarlierThanNext(Employee currentEmployee, Employee nextEmployee) {
        return currentEmployee.getDateTo().isBefore(nextEmployee.getDateTo());
    }

    private boolean checkIfCurrentStartLaterThanNext(Employee currentEmployee, Employee nextEmployee) {
        return currentEmployee.getDateFrom().isAfter(nextEmployee.getDateFrom());
    }
}
