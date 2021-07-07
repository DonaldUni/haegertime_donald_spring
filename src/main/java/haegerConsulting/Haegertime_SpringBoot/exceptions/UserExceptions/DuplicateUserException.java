package haegerConsulting.Haegertime_SpringBoot.exceptions.UserExceptions;

public class DuplicateUserException extends Exception{

    public DuplicateUserException(){

        super("This User already exist in the database");
    }
}
