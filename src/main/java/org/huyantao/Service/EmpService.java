package org.huyantao.Service;

import org.huyantao.pojo.Emp;
import org.huyantao.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
    PageBean page(Integer page, Integer pageSize,
                  String name, Short gender,
                  LocalDate begin,
                  LocalDate end);

    void delete(List<Integer> ids);
    void save(Emp emp);


    //根据id查询员工
    Emp getById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
