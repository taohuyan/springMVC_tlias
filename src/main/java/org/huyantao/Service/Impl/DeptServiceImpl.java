package org.huyantao.Service.Impl;

import org.huyantao.Mapper.DeptMapper;
import org.huyantao.Mapper.EmpMapper;
import org.huyantao.pojo.Dept;
import org.huyantao.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    //service中DeptServiceImpl不能操作数据库
    // 需要调用mapper中的方法 使用注入的方法
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }


    @Transactional(rollbackFor = Exception.class ) //事务管理 默认是运行时异常
    @Override
    public void delete(Integer id) throws Exception {
            //根据id删除部门
            deptMapper.deleteById(id);
            //模拟：异常
//            if(true){
//                throw new Exception("出现异常了~~~");
//            }
//            //根据部门id删除该部门下的员工
            empMapper.deleteByDeptId(id);
    }




    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);


    }


    @Override
    public Dept getById(Integer id) {
        Dept dept = deptMapper.getById(id);
        return dept;
    }



    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);

    }
}
