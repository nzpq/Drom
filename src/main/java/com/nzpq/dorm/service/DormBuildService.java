package com.nzpq.dorm.service;

import com.nzpq.dorm.domain.DormBuild;

import java.util.List;

public interface DormBuildService {
    List<DormBuild> findAll();

    DormBuild findOne(String name,String bid);

    void addBuild(DormBuild build);

    void updateBuild(DormBuild build);

    void activeBuild(Integer bid,Integer disabled);

    void deleteBuild(Integer bid,Integer disabled);

    List<DormBuild> findName();
}
