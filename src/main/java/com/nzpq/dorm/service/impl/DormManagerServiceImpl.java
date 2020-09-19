package com.nzpq.dorm.service.impl;

import com.nzpq.dorm.dao.DormBuildDao;
import com.nzpq.dorm.dao.DormManagerDao;
import com.nzpq.dorm.dao.impl.DormBuildDaoImpl;
import com.nzpq.dorm.dao.impl.DormManagerDaoImpl;
import com.nzpq.dorm.domain.DormBuild;
import com.nzpq.dorm.domain.User;
import com.nzpq.dorm.service.DormManagerService;

import java.util.List;

public class DormManagerServiceImpl implements DormManagerService {

    private DormManagerDao dormManagerDao = new DormManagerDaoImpl();
    private DormBuildDao dormBuildDao = new DormBuildDaoImpl();

    @Override
    public List<User> findAll() {
        List<User> users = dormManagerDao.findManager();
        for (User user : users) {
            List<DormBuild> builds = dormBuildDao.findByUid(user.getId());
            user.setBuilds(builds);
        }
        return users;
    }

    @Override
    public Integer addManager(User user) {
       //调用dao完成保存宿管
        return dormManagerDao.addManager(user);
    }

    @Override
    public void addManagerToBuild(Integer uid, String[] dormBuildIds) {
        //调用dao保存中间表信息 tb_manage_dormBuildId
        for(String buildId : dormBuildIds){
            dormManagerDao.addManagerToBuild(uid,Integer.valueOf(buildId));
        }
    }

    @Override
    public User findManagerByCode(String stuCode) {
        return dormManagerDao.findByCode(stuCode);
    }

    @Override
    public User findOne(Integer uid) {
        return dormManagerDao.findById(uid);
    }

    @Override
    public List<DormBuild> findBuildByUid(Integer uid) {
        return dormBuildDao.findByUid(uid);
    }

    @Override
    public void updateManager(User user, String[] dormBuildIds) {
        //封装user
        User review_user = dormManagerDao.findById(user.getId());
//        user.setStuCode(review_user.getStuCode());
        user.setId(review_user.getId());
        user.setRoleId(review_user.getRoleId());
        user.setCreateUserId(review_user.getCreateUserId());
        user.setDisabled(review_user.getDisabled());
        //调用dao完成保存
        dormManagerDao.updateManager(user);
        //调用dao跟新中间表
        //先把原来中间表的数据都删除
        dormManagerDao.deleteAll(user.getId());
        //再重新插入
        for (String buildId : dormBuildIds){
            dormManagerDao.addManagerToBuild(user.getId(),Integer.valueOf(buildId));
        }

    }

    @Override
    public void activeOrDelete(Integer uid, Integer disabled) {
        if(disabled == 0){
            //调用激活
            dormManagerDao.activeDisabled(uid);
        }
        if(disabled == 1){
            //调用删除
            dormManagerDao.deleteDisabled(uid);
        }
    }
}
