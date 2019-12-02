package fr.gtm.bovoyages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Erwan Soubeyrand, Denis Kuçuk, Jonathan Dimur.
 * @version 1.0
 * Classe Main servant à SpringBoot pour demarrer l'application.
 *
 */
@SpringBootApplication
@EnableAsync
public class BovoyagesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BovoyagesApplication.class, args);
    }

}
