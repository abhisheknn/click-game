package com.micro.game.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.spi.ExecutorServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.micro.game.mqtt.MQTTConnector;

@Component
public class GameMQTTListner {

	@Autowired
	UserService userService;
	
	@PostConstruct
	public void init() {
		 ExecutorService executorService = Executors.newSingleThreadExecutor();
		 executorService.execute(()->{
			 userService.reciveFromMQTTBroker("incoming_user_click");
		 });
		 
	}
}
