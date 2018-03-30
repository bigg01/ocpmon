package ch.oliverg.OcpMon;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ch.oliverg.OcpMon.services.OcpService;



@SpringBootApplication
public class OcpmonApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(OcpmonApplication.class, args);


    }

}