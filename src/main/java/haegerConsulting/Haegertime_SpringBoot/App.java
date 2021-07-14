package haegerConsulting.Haegertime_SpringBoot;

import haegerConsulting.Haegertime_SpringBoot.exceptions.DuplicateException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.ElementNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.UsernameEmptyException;
import haegerConsulting.Haegertime_SpringBoot.model.*;
import haegerConsulting.Haegertime_SpringBoot.model.builder.UserBuilder;
import haegerConsulting.Haegertime_SpringBoot.model.builder.WorktimeBuilder;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Power;
import haegerConsulting.Haegertime_SpringBoot.repository.UserRepository;
import haegerConsulting.Haegertime_SpringBoot.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(App.class, args);

		saveInDatabase(configurableApplicationContext);
	}

	public static void saveInDatabase(ConfigurableApplicationContext context){
		saveUserInDatabase(context);
		saveCustomersInDatabase(context);
		saveProjectsInDatabase(context);
		saveWorktimesInDatabase(context);
		saveRequestOfHolidaysINDatabase(context);
	}

	public static void saveUserInDatabase(ConfigurableApplicationContext context){

		var user = new UserBuilder().personId(0L).employeeNummer(5).userName("Sebas").lastname("Schwarz").firstname("Sebastien").password("password0").email("sebastien@gmail.com")
				.numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();
		var user1 = new UserBuilder().personId(0L).employeeNummer(6).userName("bara").lastname("Weiß").firstname("Barbara").password("password1").email("weiß_barbara@gmail.com")
				.power(Power.Bookkeeper).numberOfUsedHoliday(10).numberOfRestHoliday(20).numberOfSickDay(2).build();
		var user2 = new UserBuilder().personId(0L).employeeNummer(7).userName("JansK").lastname("Krüger").firstname("Jans").password("password2").email("jans_kruger@gmail.com")
				.power(Power.Administrator).numberOfUsedHoliday(15).numberOfRestHoliday(15).numberOfSickDay(0).build();

		List<User> users = new ArrayList<>();
		users.add(user);
		users.add(user1);
		users.add(user2);

		UserService userService = context.getBean(UserService.class);
		users.forEach(u ->{
			try {
				userService.create(u);
			} catch (DuplicateException | UsernameEmptyException e) {
				e.printStackTrace();
			}
		});
	}

	public static void saveCustomersInDatabase(ConfigurableApplicationContext context){

		var project = new Project("Test1", "Beschreibung von Test1.");
		var project1 = new Project("Test2", "Beschreibung von Test2.");
		List<Project> projects = new ArrayList<>();
		projects.add(project);
		projects.add(project1);

		var project2 = new Project("Test3", "Beschreibung von Test3.");
		var project3 = new Project("Test4", "Beschreibung von Test4.");
		List<Project> projects1 = new ArrayList<>();
		projects1.add(project2);
		projects1.add(project3);

		var customer = new Customer(1L, "Mbappe", "Junior", "PSG", projects);
		var customer1 = new Customer(2L, "Abc", "Def", "Hij", projects1);

		List<Customer> customers = new ArrayList<>();
		customers.add(customer);
		customers.add(customer1);


		CustomerService customerService = context.getBean(CustomerService.class);
		customers.forEach(c -> {
			try {
				customerService.create(c);
			} catch (DuplicateException e) {
				e.printStackTrace();
			}
		});
	}

	public static void saveProjectsInDatabase(ConfigurableApplicationContext context){

		Project project = new Project("Autohaus", "XMLlesen und PDF erzeugen");
		Project project1 = new Project("Haegertime mit JSE", "Haegertime mit Java Standart implementieren");
		Project project2 = new Project("Haegertime mit Spring Boot", "Haegertime mit Spring Boot, JPA, Hibernate implementieren");

		List<Project> projects = new ArrayList<>();
		projects.add(project);
		projects.add(project1);
		projects.add(project2);

		ProjectService projectService = context.getBean(ProjectService.class);
		projects.forEach(p -> {
			try {
				projectService.create(p);
			} catch (DuplicateException e) {
				e.printStackTrace();
			}
		});
	}

	public static void saveWorktimesInDatabase(ConfigurableApplicationContext context){


		WorktimeService worktimeService = context.getBean(WorktimeService.class);

		List<Worktime> worktimes = new ArrayList<>();

		Worktime worktime1 = new WorktimeBuilder().workhour(10).overtime(0).undertime(0).period("24.07.2021-31.07.2021").build();
		Worktime worktime2 = new WorktimeBuilder().workhour(20).overtime(0).undertime(0).period("24.07.2021-31.07.2021").build();
		Worktime worktime3 = new WorktimeBuilder().workhour(30).overtime(0).undertime(0).period("24.07.2021-31.07.2021").build();

		worktimes.add(worktime1);
		worktimes.add(worktime2);
		worktimes.add(worktime3);

		worktimes.forEach(worktime -> {
			try {
				worktimeService.create(worktime);
			} catch (DuplicateException e) {
				e.printStackTrace();
			}
		});
	}

	public static void saveRequestOfHolidaysINDatabase(ConfigurableApplicationContext context){

		var requestOfHoliday = new RequestOfHoliday( 10, Instant.now().plus(Duration.ofDays(10)), Instant.now().plus(Duration.ofDays(20)));
		var requestOfHoliday1 = new RequestOfHoliday( 4, Instant.now().plus(Duration.ofDays(15)), Instant.now().plus(Duration.ofDays(25)));

		List<RequestOfHoliday> requestOfHolidays = new ArrayList<>();
		requestOfHolidays.add(requestOfHoliday);
		requestOfHolidays.add(requestOfHoliday1);

		RequestOfHolidaysService requestOfHolidayService = context.getBean(RequestOfHolidaysService.class);
		requestOfHolidays.forEach(request -> {
			try {
				requestOfHolidayService.create(request);
			} catch (DuplicateException e) {
				e.printStackTrace();
			}
		});
	}







}
