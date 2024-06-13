package school.sptech.naumspringapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NaumSpringApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NaumSpringApiApplication.class, args);
	}

}
