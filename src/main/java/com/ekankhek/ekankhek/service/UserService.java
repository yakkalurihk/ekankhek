package com.ekankhek.ekankhek.service;

import com.ekankhek.ekankhek.domain.User;

public interface UserService {

	User findByUsername(String email);
}
