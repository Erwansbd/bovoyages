package fr.gtm.bovoyages.mail;

import org.springframework.stereotype.Component;


public interface MessageSender {
	
	public void send(Message msg);

}
