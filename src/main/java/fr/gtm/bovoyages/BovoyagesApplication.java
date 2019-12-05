package fr.gtm.bovoyages;

import java.awt.image.ImageFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Erwan Soubeyrand, Denis Kuçuk, Jonathan Dimur.
 * @version 1.0
 * Classe Main servant à SpringBoot pour demarrer l'application.
 *
 */
@SpringBootApplication
@EnableAsync
@Controller
public class BovoyagesApplication implements ErrorController {

    private static final String PATH = "/error";

    public static void main(String[] args) {
        SpringApplication.run(BovoyagesApplication.class, args);
    }

    @RequestMapping(value = PATH)
    public String error() {
        return "forward:/index.html";
    }


    @Override
    public String getErrorPath() {
        return PATH;
    }
}
