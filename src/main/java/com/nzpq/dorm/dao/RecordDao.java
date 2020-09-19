package com.nzpq.dorm.dao;

import com.nzpq.dorm.domain.Record;
import com.nzpq.dorm.domain.User;

import java.util.List;

public interface RecordDao {

    List<Record> findAllRecord();

    void deleteDisabled(Integer rid );

    void activeDisabled(Integer rid);

    void addRecord(Record record);

    User findByStucode(Integer stuCode);

    Record findById(Integer rid);

    void updateRecord(Record record);
}
