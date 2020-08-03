package com.server;

import com.server.model.Data;
import com.server.model.Request;
import com.server.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class WebSocketController {

//	@MessageMapping("/hello")
//	@SendTo("/topic/greetings")
//	public Message greeting(Message message) throws Exception {
//		log.info("Controller called");
////		log.info("From " + message.getFrom() + " content: " + message.getText());
//////		Thread.sleep(6000);
////		Message message1 = new Message();
////		message1.setFrom(message.getFrom());
////		message1.setText("Hi friend");
//		return message1;
//	}

	@MessageMapping("/topic/greetings")
//	@SendTo("/topic/greetings1")
	public Message greeting1(Message<Request> message)  {
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

	@MessageMapping("/merchant/qrc-data")
	@SendTo("/topic/merchant/qrc-data")
	public Message<Response> merchantQrcData(Message<Request> message) throws InterruptedException {
		log.info("Controller merchantQrcData called");
		log.info("Message ID " + message.getHeaders().get("simpSessionId"));
		Response response = new Response();
		for (int i = 0; i < 2; i++) {
			Thread.sleep(1000);
			log.info("....... {}", i);
		}
		response.setCode("000");
		Data data = new Data();
		data.setMessage("OK");
		data.setPayload("string payload");
		response.setData(data);
		return MessageBuilder.withPayload(response).build();
	}
}
