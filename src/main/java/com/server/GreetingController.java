package com.server;

import com.server.model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;


@Controller
public class GreetingController {

	Logger log = LogManager.getLogger(GreetingController.class);

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Message greeting(Message message) throws Exception {
		log.info("Controller called");
		log.info("From " + message.getFrom() + " content: " + message.getText());
//		Thread.sleep(6000);
		Message message1 = new Message();
		message1.setFrom(message.getFrom());
		message1.setText("Hi friend");
		return message1;
	}

	@MessageMapping("/hello1")
	@SendTo("/topic/greetings1")
	public org.springframework.messaging.Message greeting1(org.springframework.messaging.Message<Request> message) throws Exception {
		log.info("Controller greetings1 called");
//		log.info("From " + message.getFrom() + " content: " + message.getText());
		log.info("Message headers " + message.getHeaders());
		log.info("Message payload " + message.getPayload());
//		Thread.sleep(6000);
		org.springframework.messaging.Message<String> test =
				MessageBuilder.withPayload("test").build();
//		message1.setFrom(message.getFrom() + "greetings1");
//		message1.setText("Hi friend");
		return test;
	}
}
