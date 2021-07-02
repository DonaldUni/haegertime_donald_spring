package haegerConsulting.Haegertime_SpringBoot;

import haegerConsulting.Haegertime_SpringBoot.model.User;
import haegerConsulting.Haegertime_SpringBoot.repository.FinalWorktimeRepository;
import haegerConsulting.Haegertime_SpringBoot.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);


	}

	public void test(FinalWorktimeRepository userRepository){

		//userRepository.
	}

}
