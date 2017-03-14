package com.pedroaugust8.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedroaugust8.common.models.User;
import com.pedroaugust8.common.interfaces.UserRepository;
import com.pedroaugust8.common.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository UserRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository UserRepository) {
		this.UserRepository = UserRepository;
	}
	
    public List<User> list() {
        List<User> UserList = UserRepository.list();
		return UserList; 
    }

    public User get(String id) {
        return UserRepository.get(id);
    }

    public void save(User user) {
        UserRepository.save(user);
    }

    public void delete(String id) {
        UserRepository.delete(id);
    }

}