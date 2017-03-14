package com.pedroaugust8.service.controller;

import com.netflix.hystrix.exception.HystrixRuntimeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedroaugust8.common.models.User;
import com.pedroaugust8.service.gateway.UserServiceGateway;
import com.pedroaugust8.service.model.UserComposite;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/async")
public class AsyncUserController {

  private static final Logger LOG = LoggerFactory.getLogger(AsyncUserController.class);

  @Autowired
  UserServiceGateway userServiceGateway;

  @RequestMapping("/{userId}")
  public ResponseEntity<UserComposite> getProduct(@PathVariable int userId) throws InterruptedException, ExecutionException {

    Future<ResponseEntity<User>> userResult = null;
    try {
      userResult = userServiceGateway.getUserAsync(userId);
    } catch (HystrixRuntimeException e) {
      if (e.getCause().getClass().equals(RejectedExecutionException.class) ) {
        LOG.warn("********************* User-Service concurrency limit reached *************************)");
        return new ResponseEntity<>(null, HttpStatus.TOO_MANY_REQUESTS);
      }
    }

    if (userResult.get().getBody()== null) {
      return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
    }

    final User user = userResult.get().getBody();

    UserComposite result = new UserComposite();
    result.setUser(user);
    return new ResponseEntity<UserComposite>(result, userResult.get().getStatusCode());
  }

}
