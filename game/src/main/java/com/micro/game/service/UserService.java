package com.micro.game.service;

import com.micro.game.pojo.User;

public interface UserService {

	public void sendToMQTTBroker(String topicName,User user);
	public void reciveFromMQTTBroker(String topicName);
}
