package com.sirma.task.sirma.model;

import java.time.LocalDate;

/**
 * Model class represents an employee.
 */
public class Employee {

    private Integer employeeId;
    private Integer projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    /**
     * Constructor.
     *
     * @param employeeId employee unique identifier from csv document.
     * @param projectId  project unique identifier on which the employee works.
     * @param dateFrom   the date when the employee started to work on a specific project.
     * @param dateTo     the last date of the employee in the specific project.
     */
    public Employee(Integer employeeId, Integer projectId, LocalDate dateFrom, LocalDate dateTo) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
