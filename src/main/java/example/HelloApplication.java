package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RestController
@SpringBootApplication
public class HelloApplication {

	@RequestMapping("/")
	public String index() {
        	return "Greetings from Spring Boot!";
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class);
	}
}
