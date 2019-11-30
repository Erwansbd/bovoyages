package fr.gtm.bovoyages.mail;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;


@ComponentScan
public class MainMessageSpringAnnotation {

	public static void main(String[] args) {
		try(AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(MainMessageSpringAnnotation.class)) {
			EmailSender es = ctx.getBean(EmailSender.class);
			Message msg = ctx.getBean(Message.class);
			es.send(msg);
		}

	}

}

