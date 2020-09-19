package com.nzpq.dorm.dao;

import com.nzpq.dorm.domain.User;

import java.util.List;

public interface StudentDao {
    List<User> findAll(Integer uid,Integer roleId);

    void addStudent(User user);

    void delete(Integer uid);

    void active(Integer uid);

    User findByUid(Integer uid);

    void updateStudent(User user);
}
