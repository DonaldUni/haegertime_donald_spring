package haegerConsulting.Haegertime_SpringBoot;

import haegerConsulting.Haegertime_SpringBoot.exceptions.DuplicateException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.ElementNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.UsernameEmptyException;
import haegerConsulting.Haegertime_SpringBoot.model.*;
import haegerConsulting.Haegertime_SpringBoot.model.builder.UserBuilder;
import haegerConsulting.Haegertime_SpringBoot.model.builder.WorktimeBuilder;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Power;
import haegerConsulting.Haegertime_SpringBoot.services.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
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

		var user = new UserBuilder().employeeNummer(5).userName("Sebas").lastname("Schwarz").firstname("Sebastien").password("password0").email("sebastien@gmail.com")
				.numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();
		var user1 = new UserBuilder().employeeNummer(6).userName("bara").lastname("Weiß").firstname("Barbara").password("password1").email("weiß_barbara@gmail.com")
				.power(Power.Bookkeeper).numberOfUsedHoliday(10).numberOfRestHoliday(20).numberOfSickDay(2).build();
		var user2 = new UserBuilder().employeeNummer(7).userName("JansK").lastname("Krüger").firstname("Jans").password("password2").email("jans_kruger@gmail.com")
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

		//projects
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

		//customers
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

		var projectService = context.getBean(ProjectService.class);
		var customerService = context.getBean(CustomerService.class);
		var userService = context.getBean(UserService.class);

		//Customers
		Customer customer = null;
		Customer customer1 = null;

		try {
			customer = customerService.getCustomerByCustomerId(1L);
			customer1 = customerService.getCustomerByCustomerId(2L);
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		}

		//users
		User user = null;
		User user1 = null;

		try {
			user = userService.getUserByEmployeeNummer(5);
			user1 = userService.getUserByEmployeeNummer(6);
		}catch (ElementNotFoundException e) {
			e.printStackTrace();
		}
		List<User> users = Arrays.asList(user, user1);

		//projects
		Project project = new Project("Autohaus", "XMLlesen und PDF erzeugen", customer, users);
		Project project1 = new Project("Haegertime mit JSE", "Haegertime mit Java Standart implementieren",customer1);
		//Project project2 = new Project("Haegertime mit Spring Boot", "Haegertime mit Spring Boot, JPA, Hibernate implementieren", customer, users);

		List<Project> projects = new ArrayList<>();
		projects.add(project);
		projects.add(project1);
		//projects.add(project2);

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
		ProjectService projectService = context.getBean(ProjectService.class);
		UserService userService = context.getBean(UserService.class);

		//users
//		var user = new UserBuilder().id(0L).employeeNummer(5).userName("Sebas").lastname("Schwarz").firstname("Sebastien").password("password0").email("sebastien@gmail.com")
//				.numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();
//
//		var user1 = new UserBuilder().id(1L).employeeNummer(6).userName("bara").lastname("Weiß").firstname("Barbara").password("password1").email("weiß_barbara@gmail.com")
//				.power(Power.Bookkeeper).numberOfUsedHoliday(10).numberOfRestHoliday(20).numberOfSickDay(2).build();
		User user = null;
		User user1 = null;
		try {
			user = userService.getUser(1);
			user1 = userService.getUser(2);
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		}
		//project
		Project project = null;
		Project project1 = null;
		try {
			project = projectService.getProject(1L);
			project1 = projectService.getProject(2L);
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		}

		//Worktimes
		List<Worktime> worktimes = new ArrayList<>();

		//.user(user)
		Worktime worktime1 = new WorktimeBuilder().project(project).user(user).workhour(10).overtime(10).undertime(0).period("24.07.2021-31.07.2021").build();
		Worktime worktime2 = new WorktimeBuilder().project(project1).user(user).workhour(20).overtime(20).undertime(10).period("24.07.2021-31.07.2021").build();
		Worktime worktime3 = new WorktimeBuilder().project(project1).user(user1).workhour(30).overtime(15).undertime(0).period("24.07.2021-31.07.2021").build();

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

		var requestOfHolidayService = context.getBean(RequestOfHolidaysService.class);
		var userService = context.getBean(UserService.class);

		//users
		User user = null;
		User user1 = null;
		try {
			user = userService.getUser(1L);
			user1 = userService.getUser(2L);
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		}

		var requestOfHoliday = new RequestOfHoliday(user, 10, Instant.now().plus(Duration.ofDays(10)), Instant.now().plus(Duration.ofDays(20)));
		var requestOfHoliday1 = new RequestOfHoliday(user1, 4, Instant.now().plus(Duration.ofDays(15)), Instant.now().plus(Duration.ofDays(25)));

		List<RequestOfHoliday> requestOfHolidays = new ArrayList<>();
		requestOfHolidays.add(requestOfHoliday);
		requestOfHolidays.add(requestOfHoliday1);


		requestOfHolidays.forEach(request -> {
			try {
				requestOfHolidayService.create(request);
			} catch (DuplicateException e) {
				e.printStackTrace();
			}
		});
	}
}
