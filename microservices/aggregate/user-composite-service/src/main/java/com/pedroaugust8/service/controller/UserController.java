package com.pedroaugust8.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedroaugust8.common.models.User;
import com.pedroaugust8.service.gateway.UserServiceGateway;
import com.pedroaugust8.service.model.UserComposite;

import java.util.Arrays;
import java.util.Calendar;

@RestController
public class UserController {

  private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

  @Autowired
  UserServiceGateway userServiceGateway;

  @RequestMapping("/")
  public String getProduct() {
    return "{\"timestamp\":\"" + Calendar.getInstance().getTime() + "\",\"content\":\"Hello from UserAPi\"}";
  }

  @RequestMapping("/{userId}")
  public ResponseEntity<UserComposite> getProduct(@PathVariable int userId) {

    // 1. First get mandatory product information
    ResponseEntity<User> userResult = userServiceGateway.getUser(userId);

    if (!userResult.getStatusCode().is2xxSuccessful()) {
      LOG.error("Could not retrieve user {} from user-service.  Http code - {}", userId, userResult.getStatusCode());
      return new ResponseEntity<>(null, userResult.getStatusCode());
    }
   
    UserComposite result = new UserComposite();
    result.setUser(userResult.getBody());
    return new ResponseEntity<UserComposite>(result, userResult.getStatusCode());
  }

}
