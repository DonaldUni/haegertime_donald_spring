package haegerConsulting.Haegertime_SpringBoot.exceptions.Customer;

public class DuplicateCustomerException extends Exception{

    public DuplicateCustomerException() {
        super("This Customer already exist in the database");
    }
}
