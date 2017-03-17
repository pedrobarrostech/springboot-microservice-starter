package com.pedroaugust8.service.gateway.service;

import feign.Param;
import feign.RequestLine;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pedroaugust8.common.models.User;

public interface UserService {
  @RequestLine("GET /users")
  List<User> list();

  @RequestLine("GET /users/{id}")
  User get(@Param("id") String id);

  @RequestLine("POST /users")
  ResponseEntity<User> save(User user);

  @RequestLine("DELETE /users/{id}")
  ResponseEntity<User> delete(@Param("id") String id);
}

