package com.ekankhek.ekankhek.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekankhek.ekankhek.dao.UserRepository;
import com.ekankhek.ekankhek.domain.User;
import com.ekankhek.ekankhek.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User findByUsername(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(email);
	}

}
