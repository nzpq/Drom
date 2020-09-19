package com.nzpq.dorm.service;

import com.nzpq.dorm.domain.Record;
import com.nzpq.dorm.domain.User;

import java.util.List;

public interface RecordService {
    List<Record> findAll();

    void updateDisabled(Integer rid, Integer disbled);

    void addRecord(Record record);

    User findOne(Integer stuCode);

    Record findRecord(Integer rid);

    void updateRecord(Record record);
}
