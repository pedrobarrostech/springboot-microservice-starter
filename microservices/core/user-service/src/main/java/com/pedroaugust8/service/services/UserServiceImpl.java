package com.pedroaugust8.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedroaugust8.common.models.User;
import com.pedroaugust8.common.interfaces.UserRepository;
import com.pedroaugust8.common.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
    public List<User> list() {
		return userRepository.list();
    }

	@Override
    public User get(String id) {
        return userRepository.get(id);
    }

	@Override
    public void save(User user) {
        userRepository.save(user);
    }

	@Override
    public void delete(String id) {
        userRepository.delete(id);
    }

}