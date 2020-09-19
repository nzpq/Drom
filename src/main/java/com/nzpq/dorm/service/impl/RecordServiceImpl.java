package com.nzpq.dorm.service.impl;

import com.nzpq.dorm.dao.RecordDao;
import com.nzpq.dorm.dao.StudentDao;
import com.nzpq.dorm.dao.impl.RecordDaoImpl;
import com.nzpq.dorm.dao.impl.StudentDaoImpl;
import com.nzpq.dorm.domain.Record;
import com.nzpq.dorm.domain.User;
import com.nzpq.dorm.service.RecordService;

import java.util.List;

public class RecordServiceImpl implements RecordService {

    private RecordDao recordDao = new RecordDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Record> findAll() {
        //封装Record
        List<Record> records = recordDao.findAllRecord();
        //封装每条缺勤记录对应的学生信息
        for(Record record : records){
            User user = studentDao.findByUid(record.getStudentId());
            record.setUser(user);
        }
        return records;
    }

    @Override
    public void updateDisabled(Integer rid, Integer disabled) {
        if(disabled == 0){
            //调用删除
            recordDao.deleteDisabled(rid);
        }
        if(disabled == 1){
            //调用激活
            recordDao.activeDisabled(rid);
        }
    }

    @Override
    public void addRecord(Record record) {
        record.setDisabled(0);
        recordDao.addRecord(record);
    }

    @Override
    public User findOne(Integer stuCode) {
        return recordDao.findByStucode(stuCode);
    }

    @Override
    public Record findRecord(Integer rid) {
        Record record = recordDao.findById(rid);
        User user = studentDao.findByUid(record.getStudentId());
        record.setUser(user);
        return record;
    }

    @Override
    public void updateRecord(Record record) {
        recordDao.updateRecord(record);
    }
}
