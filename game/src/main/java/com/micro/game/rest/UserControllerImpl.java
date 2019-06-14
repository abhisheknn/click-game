package com.micro.game.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.micro.game.pojo.User;
import com.micro.game.service.UserService;

@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

	@Override
	public void send(String topic, User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recive(String policy) {
		// TODO Auto-generated method stub
		
	}



}
