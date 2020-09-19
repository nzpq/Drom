package com.nzpq.dorm.dao;

import com.nzpq.dorm.domain.User;

import java.util.List;

public interface DormManagerDao {

    List<User> findManager();

    Integer addManager(User user);

    void addManagerToBuild(Integer id, Integer buildId);

    User findByCode(String stuCode);

    User findById(Integer uid);

    void updateManager(User user);

    void deleteAll(Integer id);

    void activeDisabled(Integer uid);

    void deleteDisabled(Integer uid);
}
