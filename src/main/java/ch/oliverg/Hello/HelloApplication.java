package ch.oliverg.Hello;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ch.oliverg.Hello.services.OcpService;



@SpringBootApplication
public class HelloApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloApplication.class, args);


    }

}