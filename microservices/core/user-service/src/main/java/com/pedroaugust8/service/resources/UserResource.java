package com.pedroaugust8.service.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pedroaugust8.common.models.User;
import com.pedroaugust8.common.interfaces.UserService;

@RestController
@RequestMapping("users")
public class UserResource {

	private UserService userProvider;
	
	@Autowired
	public UserResource(UserService userProvider) {
		this.userProvider = userProvider;
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public List<User> getUsers(){
		return userProvider.list();
	}
	
}