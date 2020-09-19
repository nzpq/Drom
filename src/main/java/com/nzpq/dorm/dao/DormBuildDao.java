package com.nzpq.dorm.dao;

import com.nzpq.dorm.domain.DormBuild;

import java.util.List;

public interface DormBuildDao {
    List<DormBuild> findAll();

    DormBuild findOne(String name,String bid);

    void addDormBuild(DormBuild build);

    void updateById(DormBuild build);

    void updateDisabled(Integer bid,Integer disabled);

    List<DormBuild> findByUid(Integer id);

    List<DormBuild> findName();
}
