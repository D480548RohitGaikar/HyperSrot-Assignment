package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entities.User;

public interface UserService {

	User addNewUser(User user);

	List<User> getUsers();

	Optional<User> getUserById(Long userId);
}
