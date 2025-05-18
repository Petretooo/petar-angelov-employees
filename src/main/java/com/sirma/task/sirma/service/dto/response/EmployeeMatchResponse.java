package com.sirma.task.sirma.service.dto.response;

/**
 * Response that return information about employees that worked on same project.
 */
public class EmployeeMatchResponse {

    private Integer firstEmployeeId;
    private Integer secondEmployeeId;
    private Integer projectId;
    private Long workedDays;

    /**
     * Constructor.
     *
     * @param firstEmployeeId  this is a unique identifier for first employee
     * @param secondEmployeeId this is a unique identifier for second employee
     * @param projectId        this is a unique identifier for project.
     * @param workedDays       this is how long both employees worked together on the same project.
     */
    public EmployeeMatchResponse(Integer firstEmployeeId, Integer secondEmployeeId, Integer projectId, Long workedDays) {
        this.firstEmployeeId = firstEmployeeId;
        this.secondEmployeeId = secondEmployeeId;
        this.projectId = projectId;
        this.workedDays = workedDays;
    }

    public Integer getFirstEmployeeId() {
        return firstEmployeeId;
    }

    public void setFirstEmployeeId(Integer firstEmployeeId) {
        this.firstEmployeeId = firstEmployeeId;
    }

    public Integer getSecondEmployeeId() {
        return secondEmployeeId;
    }

    public void setSecondEmployeeId(Integer secondEmployeeId) {
        this.secondEmployeeId = secondEmployeeId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Long getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(Long workedDays) {
        this.workedDays = workedDays;
    }
}
