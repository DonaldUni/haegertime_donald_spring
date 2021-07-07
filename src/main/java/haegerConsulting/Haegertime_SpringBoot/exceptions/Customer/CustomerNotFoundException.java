package haegerConsulting.Haegertime_SpringBoot.exceptions.Customer;

public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException() {
        super("This Customer have been not found");
    }
}
