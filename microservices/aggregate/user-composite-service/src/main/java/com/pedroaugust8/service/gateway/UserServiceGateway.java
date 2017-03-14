package com.pedroaugust8.service.gateway;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pedroaugust8.common.models.User;
import com.pedroaugust8.service.gateway.service.UserService;

import java.util.concurrent.Future;

@Service
public class UserServiceGateway {

  private static final Logger LOG = LoggerFactory.getLogger(UserServiceGateway.class);

  @Autowired
  private UserService userService;

  @HystrixCommand(fallbackMethod = "defaultUser")
  public ResponseEntity<User> getUser(int userId) {
    return userService.getUser(userId);
  }

  @HystrixCommand(fallbackMethod = "defaultUser"
      //      , groupKey="userRateLimiter", threadPoolProperties = { @HystrixProperty(name = "coreSize", value = "5") }
      )
  public Future<ResponseEntity<User>> getUserAsync(final int userId) {
    return new AsyncResult<ResponseEntity<User>>() {
      @Override
      public ResponseEntity<User> invoke() {
        return getUser(userId);
      }
    };
  }

  public ResponseEntity<User> defaultUser(int usertId) {
    LOG.warn("Using fallback method for user-service {} ");
    return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
  }

}
