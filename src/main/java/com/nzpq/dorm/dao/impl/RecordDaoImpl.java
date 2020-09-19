package com.nzpq.dorm.dao.impl;

import com.nzpq.dorm.dao.RecordDao;
import com.nzpq.dorm.domain.Record;
import com.nzpq.dorm.domain.User;
import com.nzpq.dorm.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RecordDaoImpl implements RecordDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<Record> findAllRecord() {
        String sql = "select * from tb_record ";
        return template.query(sql,new BeanPropertyRowMapper<>(Record.class));
    }

    @Override
    public void deleteDisabled(Integer rid ) {
        String sql = "update tb_record set disabled = ? where id = ? ";
        template.update(sql,1,rid);
    }

    @Override
    public void activeDisabled(Integer rid) {
        String sql = "update tb_record set disabled = ? where id = ? ";
        template.update(sql,0,rid);
    }

    @Override
    public void addRecord(Record record) {
        String sql = "insert into tb_record(student_id,date,remark,disabled) values(?,?,?,?)";
        template.update(sql,record.getStudentId(),record.getDate(),record.getRemark(),record.getDisabled());
    }

    @Override
    public User findByStucode(Integer stuCode) {
        User user = null;
        try {
            String sql = "select * from tb_user where role_id = ? and stu_code = ? ";
            user = template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),2,stuCode);
        }catch (Exception e){
            return null;
        }
        return user;
    }

    @Override
    public Record findById(Integer rid) {
        String sql = "select * from tb_record where id = ? ";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Record.class),rid);
    }

    @Override
    public void updateRecord(Record record) {
        String sql = "update tb_record set student_id = ?,date = ?,remark = ? where student_id = ?";
        template.update(sql,record.getStudentId(),record.getDate(),record.getRemark(),record.getId());
    }
}
