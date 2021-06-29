package haegerConsulting.Haegertime_SpringBoot.exceptions;

public class RequestOfHolidayNotFoundException extends Exception{

    public RequestOfHolidayNotFoundException() {
        super("This Request Of Holidays have been not found");
    }
}
