package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.UserDao;
import com.app.entities.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public User addNewUser(User user) {
		return userDao.save(user);
	}

	@Override
	public List<User> getUsers() {
		return userDao.findAll();
	}

	@Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userDao.findById(id).orElse(null));
    }

}
