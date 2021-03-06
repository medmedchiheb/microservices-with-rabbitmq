package com.thinktank.demo.app;


import java.io.IOException;
import java.util.List;

import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.thinktank.demo.app.amqp.QueueService;
@Scope(value = "singleton")
@Component
public class SocketHandler extends TextWebSocketHandler {

	@Autowired
	QueueService queueService;
	
	Logger logger = LoggerFactory.getLogger(SocketHandler.class);
	
	List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
	
	public static WebSocketSession session;

	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {
		
		//this.session = session;

		
		/*logger.debug("ws message: "+message);
		if (message.toString().contains("left") && message.toString().contains("top")) {
			Gson gson = new Gson();
			Position position = gson.fromJson(message.toString(), Position.class);
			queueService.sendOrder(position);
			session.sendMessage(new TextMessage("message received"));
		}*/
		
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//the messages will be broadcasted to all users.
		this.session = session;
		sessions.add(session);
	}
}
