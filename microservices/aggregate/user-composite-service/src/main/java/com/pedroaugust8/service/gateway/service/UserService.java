package com.pedroaugust8.service.gateway.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pedroaugust8.common.models.User;

@FeignClient("user-service")
public interface UserService {
  @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
  ResponseEntity<User> getUser(@PathVariable("userId") int userId);

  @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}?skipProblems=yes")
  ResponseEntity<User> getUserSP(@PathVariable("userId") int userId);
}

