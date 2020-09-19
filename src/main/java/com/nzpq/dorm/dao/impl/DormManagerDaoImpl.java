package com.nzpq.dorm.dao.impl;

import com.nzpq.dorm.dao.DormManagerDao;
import com.nzpq.dorm.domain.User;
import com.nzpq.dorm.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DormManagerDaoImpl implements DormManagerDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findManager() {
        String sql = "select * from tb_user where role_id = 1 ";
        return template.query(sql,new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public Integer addManager(User user) {
        String sql = "insert into tb_user(name,password,stu_code,sex,tel,role_id,create_user_id,disabled) values(?,?,?,?,?,?,?,?)";
        template.update(sql,user.getName(),user.getPassword(),user.getStuCode(),user.getSex(),user.getTel(),user.getRoleId(),user.getCreateUserId(),user.getDisabled());

        //获取做后插入数据的id
        return template.queryForObject(" select last_insert_id()",Integer.class);

    }

    @Override
    public void addManagerToBuild(Integer uid, Integer buildId) {
        String sql = "insert into tb_manage_dormbuild(user_id,dormbuild_id) values(?,?)";
        template.update(sql,uid,buildId);
    }

    @Override
    public User findByCode(String stuCode) {
        try{
//            System.out.println("findByCode..."+stuCode);
            String sql = "select * from tb_user where stu_code = ?";
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),stuCode);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public User findById(Integer uid) {
        String sql = "select * from tb_user where id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),uid);
    }

    @Override
    public void updateManager(User user) {
        String sql = "update tb_user set name = ? , password = ? ,  sex = ? , tel = ? , role_id = ? , create_user_id = ? , disabled = ? where id = ?";
        template.update(sql,user.getName(),user.getPassword(),user.getSex(),user.getTel(),user.getRoleId(),user.getCreateUserId(),user.getDisabled(),user.getId());

    }

    @Override
    public void deleteAll(Integer id) {
        String sql = "delete from tb_manage_dormbuild where user_id = ?";
        template.update(sql,id);
    }

    @Override
    public void activeDisabled(Integer uid) {
        String sql = "update  tb_user set disabled = ? where id = ? ";
        template.update(sql,1,uid);
    }

    @Override
    public void deleteDisabled(Integer uid) {
        String sql = "update  tb_user set disabled = ? where id = ? ";
        template.update(sql,0,uid);
    }
}
