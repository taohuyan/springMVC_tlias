package org.huyantao.Service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.huyantao.Mapper.EmpMapper;
import org.huyantao.pojo.Emp;
import org.huyantao.pojo.PageBean;
import org.huyantao.Service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


    //todo 查询记录数和获取列表数据(员工信息分页查询)
//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        //获取总记录数
//        Long count = empMapper.count();
//        //获取结果列表
//        Integer start = (page-1)*pageSize;
//        List<Emp> empList = empMapper.page(start, pageSize);
//        //封装pageBean
//        PageBean pageBean = new PageBean(count, empList);
//        return pageBean;
//    }

    //todo pagehelp(员工信息分页查询)
    @Override
    public PageBean page(Integer page, Integer pageSize,String name, Short gender,
                         LocalDate begin,LocalDate end) {
        //设置分页参数
        PageHelper.startPage(page,pageSize);
        //执行查询 ---》封装类(list类型)----》(强转为page)
        List<Emp> empList = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) empList;
        //封装pageBean
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        //补全数据
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //调用添加方法
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUserNameAndPassword(emp);
    }
}
