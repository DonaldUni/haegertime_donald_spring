package haegerConsulting.Haegertime_SpringBoot.exceptions.WorktimeExceptions;

public class WorktimeNotFoundException extends Exception{

    public WorktimeNotFoundException() {
        super("This Worktime have been not found");
    }
}
