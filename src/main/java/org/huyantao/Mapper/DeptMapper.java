package org.huyantao.Mapper;

import org.huyantao.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    /*
    查询全部部门的数据
    @return
    简单的时候采用注解实现 复杂的时候采用xml实现
     */

    @Select("select * from dept")
    List<Dept> list();

    /*
    根据id 删除部门
    @parm id
    注解实现
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    //新增部门数据
    @Insert("insert into dept(name, create_time, update_time) " +
            "values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select * from tlias.dept where id = #{id}")
    Dept getById(Integer id);

    @Update("update dept set name =#{name}, update_time = #{updateTime} where id = #{id} ")
    void update(Dept dept);


}
