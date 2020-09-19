package com.nzpq.dorm.dao;

import com.nzpq.dorm.domain.User;

public interface UserDao {

    User findByStuCodeAndPassword(String stuCode, String password);

    User findByIdAndPassword(Integer id, String oldPassword);

    void updatePassword(Integer id, String password);
}
