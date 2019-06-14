package com.micro.game.rest;

import com.micro.game.pojo.User;

public interface UserController {
public void send(String topic, User user);
void recive(String policy);
}
