package com.nzpq.dorm.dao.impl;

import com.nzpq.dorm.dao.DormBuildDao;
import com.nzpq.dorm.domain.DormBuild;
import com.nzpq.dorm.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DormBuildDaoImpl implements DormBuildDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<DormBuild> findAll() {
        String sql = "select * from tb_dormbuild ";
        return template.query(sql,new BeanPropertyRowMapper<>(DormBuild.class));
    }

    @Override
    public DormBuild findOne(String name,String bid) {
        String sql = "select * from tb_dormbuild where name = ?";

        if(bid != null){
            sql = "select * from tb_dormbuild where id = ?";
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(DormBuild.class),bid);
        }
        try{
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(DormBuild.class),name);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void addDormBuild(DormBuild build) {
        String sql = " insert into tb_dormbuild(name,remark,disabled) values(?,?,?)";
        template.update(sql,build.getName(),build.getRemark(),build.getDisabled());
    }

    @Override
    public void updateById(DormBuild build) {
        String sql = "update  tb_dormbuild set name = ?,remark = ?,disabled = ? where id = ? ";
        template.update(sql,build.getName(),build.getRemark(),build.getDisabled(),build.getId());
    }

    @Override
    public void updateDisabled(Integer bid,Integer disabled) {
        //删除
        if(disabled == 0){
            String sql = "update  tb_dormbuild set disabled = ? where id = ? ";
            template.update(sql,0,bid);
        }
        //激活
        if(disabled == 1){
            String sql = "update  tb_dormbuild set disabled = ? where id = ? ";
            template.update(sql,1,bid);
        }

    }

    /**
     * 根据uid查找，用于显示宿管管理的宿舍咯
     * @param id
     * @return
     */
    @Override
    public List<DormBuild> findByUid(Integer id) {
        String sql = "select tb_dormbuild.* from tb_manage_dormbuild INNER JOIN  tb_dormbuild on tb_dormbuild.id = tb_manage_dormbuild.dormBuild_id where user_id = ?";
        return template.query(sql,new BeanPropertyRowMapper<>(DormBuild.class),id);
    }

    @Override
    public List<DormBuild> findName() {
        String sql = "select * from tb_dormbuild where disabled = 0";
        return template.query(sql,new BeanPropertyRowMapper<>(DormBuild.class));
    }


}
