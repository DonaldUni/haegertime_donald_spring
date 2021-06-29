package haegerConsulting.Haegertime_SpringBoot.exceptions;

public class DuplicateRequestOfHoliday extends Exception{

    public DuplicateRequestOfHoliday(){

        super("This Request Of Holiday already exist in the database");
    }
}
