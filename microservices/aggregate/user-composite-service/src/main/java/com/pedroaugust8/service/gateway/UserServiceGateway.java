package com.pedroaugust8.service.gateway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pedroaugust8.common.models.User;
import com.pedroaugust8.service.gateway.service.UserService;


@RestController
@RequestMapping("users")
public class UserServiceGateway {
	
	private UserService userService;
	
	@Autowired
	public UserServiceGateway(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public List<User> list() {
		return userService.list();
	}

}
