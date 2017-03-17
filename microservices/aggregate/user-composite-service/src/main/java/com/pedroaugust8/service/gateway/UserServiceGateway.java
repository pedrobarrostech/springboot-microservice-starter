package com.pedroaugust8.service.gateway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.pedroaugust8.common.exceptions.UserException;
import com.pedroaugust8.common.models.User;
import com.pedroaugust8.service.gateway.service.UserService;

@RestController
@CrossOrigin
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
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user) throws UserException {
    		userService.save(user);
    		return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") final String id) throws UserException {
		userService.delete(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") final String id) {
        return userService.get(id);
    }

}
