package haegerConsulting.Haegertime_SpringBoot.exceptions.UserExceptions;

public class UserNotFoundExceptions extends Exception{

    public UserNotFoundExceptions() {
        super("This User have been not found");
    }
}
