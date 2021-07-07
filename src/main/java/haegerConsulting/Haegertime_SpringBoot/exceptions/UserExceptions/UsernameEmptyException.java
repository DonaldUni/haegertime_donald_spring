package haegerConsulting.Haegertime_SpringBoot.exceptions.UserExceptions;

public class UsernameEmptyException extends Exception{

    public UsernameEmptyException() {
        super("The Username is empty");
    }
}
