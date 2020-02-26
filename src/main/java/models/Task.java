package models;

import exceptions.TaskException;

import java.time.LocalDate;

public class Task {
    private String creator;
    private String description;
    private LocalDate executionDate;
    private Status status;
    private Resolution resolution;

    public Task(String creator, String description, LocalDate executionDate) throws TaskException {
        this.creator = creator;
        this.description = description;
        this.status = Status.OPEN;
        this.resolution = Resolution.WAITING;
        checkExecutionDate(executionDate);
        this.executionDate = executionDate;
    }

    public Status getStatus() {
        return status;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public void execute() {
        this.status = Status.CLOSED;
        this.resolution = Resolution.DONE;
        this.executionDate = LocalDate.now();
    }

    public void report(LocalDate newDate) throws TaskException {
        checkExecutionDate(newDate);
        this.executionDate = newDate;
    }

    public void cancel() {
        this.status = Status.CANCELED;
        this.resolution = Resolution.IGNORED;
    }

    public void checkExecutionDate(LocalDate newDate) throws TaskException {
        if(newDate.isBefore(LocalDate.now())) {
            throw new TaskException("Error : The new task excecutionDate (" + newDate + ") is set in the past");
        }
    }


    @Override
    public String toString() {
        return "Task{" +
                "creator='" + creator + '\'' +
                ", description='" + description + '\'' +
                ", executionDate=" + executionDate +
                ", status=" + status +
                ", resolution=" + resolution +
                '}';
    }

}
