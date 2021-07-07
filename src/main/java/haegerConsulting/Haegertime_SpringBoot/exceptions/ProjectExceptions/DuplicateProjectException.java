package haegerConsulting.Haegertime_SpringBoot.exceptions.ProjectExceptions;

public class DuplicateProjectException extends Exception{

    public DuplicateProjectException() {
        super("This Project already exist in the database");
    }
}
