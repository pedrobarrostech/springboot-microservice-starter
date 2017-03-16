package com.pedroaugust8.service.gateway.service;

import feign.RequestLine;

import java.util.List;

import com.pedroaugust8.common.models.User;

public interface UserService {
  @RequestLine("GET /users")
  List<User> list();

}

