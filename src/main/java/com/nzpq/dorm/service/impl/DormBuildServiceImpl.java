package com.nzpq.dorm.service.impl;

import com.nzpq.dorm.dao.DormBuildDao;
import com.nzpq.dorm.dao.impl.DormBuildDaoImpl;
import com.nzpq.dorm.domain.DormBuild;
import com.nzpq.dorm.service.DormBuildService;

import java.util.List;

public class DormBuildServiceImpl implements DormBuildService {

    private DormBuildDao dormBuildDao = new DormBuildDaoImpl();

    @Override
    public List<DormBuild> findAll() {
        return dormBuildDao.findAll();
    }

    @Override
    public DormBuild findOne(String name,String bid) {
        return dormBuildDao.findOne(name,bid);
    }

    @Override
    public void addBuild(DormBuild build) {
        dormBuildDao.addDormBuild(build);
    }

    @Override
    public void updateBuild(DormBuild build) {
        dormBuildDao.updateById(build);
    }

    @Override
    public void activeBuild(Integer bid,Integer disabled) {
        dormBuildDao.updateDisabled(bid,disabled);
    }

    @Override
    public void deleteBuild(Integer bid,Integer disabled) {
        dormBuildDao.updateDisabled(bid,disabled);
    }

    @Override
    public List<DormBuild> findName() {
        return dormBuildDao.findName();
    }
}
