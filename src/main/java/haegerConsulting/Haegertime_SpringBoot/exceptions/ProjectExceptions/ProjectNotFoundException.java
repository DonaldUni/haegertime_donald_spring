package haegerConsulting.Haegertime_SpringBoot.exceptions.ProjectExceptions;

public class ProjectNotFoundException extends Exception{

    public ProjectNotFoundException() {
        super("This Project have been not found");
    }
}
