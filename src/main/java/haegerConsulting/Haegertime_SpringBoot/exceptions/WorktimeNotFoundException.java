package haegerConsulting.Haegertime_SpringBoot.exceptions;

public class WorktimeNotFoundException extends Exception{

    public WorktimeNotFoundException() {
        super("This Worktime have been not found");
    }
}
