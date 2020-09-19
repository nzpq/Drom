package com.nzpq.dorm.dao.impl;

import com.nzpq.dorm.dao.UserDao;
import com.nzpq.dorm.domain.User;
import com.nzpq.dorm.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByStuCodeAndPassword(String stuCode, String password) {
        String sql = "select * from tb_user where stu_code = ? and password = ? and disabled = 0";
        User user = null;
        try{
            user = template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),stuCode,password);
            return user;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public User findByIdAndPassword(Integer id, String oldPassword) {

        User user = null;
        try{
            String sql = "select * from tb_user where id = ? and password = ? ";
            user = template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),id,oldPassword);
            return user;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void updatePassword(Integer id, String password) {
        String sql = "update tb_user set password = ? where id = ?";
        template.update(sql,password,id);
    }
}
