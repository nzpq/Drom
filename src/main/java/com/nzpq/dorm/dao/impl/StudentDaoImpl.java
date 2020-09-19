package com.nzpq.dorm.dao.impl;

import com.nzpq.dorm.dao.StudentDao;
import com.nzpq.dorm.domain.User;
import com.nzpq.dorm.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll(Integer uid,Integer roleId) {
        String sql;
        if(roleId == 1){
            //是宿管
            sql = "select * from tb_user where role_id = ? and dormBuildId in (select tb_dormbuild.id from tb_manage_dormbuild INNER JOIN  tb_dormbuild on tb_dormbuild.id = tb_manage_dormbuild.dormBuild_id where user_id = ?)";
            return template.query(sql,new BeanPropertyRowMapper<>(User.class),2,uid);

        }else{
            sql = "select * from tb_user where role_id = ? ";
            return template.query(sql,new BeanPropertyRowMapper<>(User.class),2);
        }

    }

    @Override
    public void addStudent(User user) {
        String sql = "insert into tb_user(name,password,stu_code,dorm_Code,sex,tel,dormBuildId,role_id,create_user_id,disabled) values(?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,user.getName(),user.getPassword(),user.getStuCode(),user.getDormCode(),user.getSex(),user.getTel(),user.getDormBuildId(),user.getRoleId(),user.getCreateUserId(),user.getDisabled());
    }

    @Override
    public void delete(Integer uid) {
        String sql = "update tb_user set disabled = ? where id = ?";
        template.update(sql,1,uid);
    }

    @Override
    public void active(Integer uid) {
        String sql = "update tb_user set disabled = ? where id = ?";
        template.update(sql,0,uid);
    }

    @Override
    public User findByUid(Integer uid) {
        String sql = "select * from tb_user where id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),uid);
    }

    @Override
    public void updateStudent(User user) {
        String sql = "update tb_user set name = ?, password = ?,sex = ?,tel = ?,dormBuildId = ?,dorm_code = ? where id = ?";
        template.update(sql,user.getName(),user.getPassword(),user.getSex(),user.getTel(),user.getDormBuildId(),user.getDormCode(),user.getId());
    }
}
