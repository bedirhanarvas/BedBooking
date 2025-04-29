package com.graduationProject.graduationProject.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.graduationProject.graduationProject.business.abstracts.UserService;
import com.graduationProject.graduationProject.dataAccess.abstracts.UserDao;
import com.graduationProject.graduationProject.entities.concretes.User;

@Service
public class UserManager  implements UserService{

	private final UserDao userDao;

    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<User>();

        userDao.findAll().forEach(users::add);

        return users;
    }
}
