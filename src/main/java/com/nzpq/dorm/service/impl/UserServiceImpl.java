package com.nzpq.dorm.service.impl;

import com.nzpq.dorm.dao.UserDao;
import com.nzpq.dorm.dao.impl.UserDaoImpl;
import com.nzpq.dorm.domain.User;
import com.nzpq.dorm.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(User user) {
        User u = userDao.findByStuCodeAndPassword(user.getStuCode(),user.getPassword());
        return u;

    }

    @Override
    public User findUser(Integer id, String oldPassword) {
        return userDao.findByIdAndPassword(id,oldPassword);
    }

    @Override
    public void updatePassword(Integer id, String password) {
        userDao.updatePassword(id,password);
    }
}
