package com.server.confNew;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
@Slf4j
@Getter
@Setter
@Component
public class CustomStompSessionHandler extends StompSessionHandlerAdapter {

    private StompSession stompSession;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        this.stompSession = session;
        log.info("New session established : " + session.getSessionId());
        session.subscribe("/topic/greetings", this);
//        session.subscribe("/topic/greetings1", this);
//        log.info("Subscribed to /topic/messages");
//        session.send("/app/hello", getSampleMessage());
//        log.info("Message sent to websocket server");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Object.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("handleFrame {}", headers);
        Message<Object> build = MessageBuilder.withPayload(payload).build();
        log.info("payload {}", build);
        }
    }



