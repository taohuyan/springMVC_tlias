package org.huyantao.Mapper;

import org.huyantao.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {

    //todo 查询记录数和获取列表数据(员工信息分页查询)
    //todo 这种方法比较繁琐 所以采用pagehelp插件解决
//    //注解 查询记录数
//    @Select("select count(*) from emp")
//    public Long count();
//    //注解 获取列表数据
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> page(Integer start,Integer pageSize);

    //todo pagehelp(员工信息分页查询)

    //todo 这里是复杂的查询语句 所以采用xml方法实现
//    @Select("select * from emp ")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    //批量删除方法员工
    void delete(List<Integer> ids);

    //新增员工
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime});")
    void insert(Emp emp);


    //根据id查询员工
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    //基于动态sql更新
    void update(Emp emp);


    //根据用户名密码查询员工
    @Select("select * from emp where username = #{username} and password =#{password}")
    Emp getByUserNameAndPassword(Emp emp);

    //根据部门id删除部门员工
    @Delete("delete from emp where dept_id = #{dept}")
    void deleteByDeptId(Integer deptId);
}
