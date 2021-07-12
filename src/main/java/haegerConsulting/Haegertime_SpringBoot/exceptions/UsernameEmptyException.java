package haegerConsulting.Haegertime_SpringBoot.exceptions;

public class UsernameEmptyException extends Exception{

    public UsernameEmptyException() {
        super("The Username is empty");
    }
}
