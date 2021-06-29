package haegerConsulting.Haegertime_SpringBoot.exceptions;

public class ProjectNotFoundException extends Exception{

    public ProjectNotFoundException() {
        super("This Project have been not found");
    }
}
