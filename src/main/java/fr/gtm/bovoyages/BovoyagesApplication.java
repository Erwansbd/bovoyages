package fr.gtm.bovoyages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BovoyagesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BovoyagesApplication.class, args);
    }

}
