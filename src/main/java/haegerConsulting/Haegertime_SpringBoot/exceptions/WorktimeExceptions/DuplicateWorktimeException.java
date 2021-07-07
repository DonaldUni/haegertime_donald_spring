package haegerConsulting.Haegertime_SpringBoot.exceptions.WorktimeExceptions;

public class DuplicateWorktimeException extends Exception{

    public DuplicateWorktimeException() {
        super("This Worktime already exist in the database");
    }
}
