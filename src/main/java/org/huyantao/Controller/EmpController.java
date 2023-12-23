package org.huyantao.Controller;

import org.huyantao.pojo.*;
import org.huyantao.Service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.huyantao.utils.aop.anno.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps") //公共部分的路径抽取出来
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){

        log.info("分页查询，参数:{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        //调用service分页查询
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);

    }


    //批量删除
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作，ids:{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    //新增
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp){
        //记录日志
        log.info("新增员工, emp:{}",emp);
        //调用业务层新增功能
        empService.save(emp);
        //响应
        return Result.success();
    }

    @GetMapping("/{id}")
    //修改员工信息
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询员工id,id:{}",id);

        //调用seivice端方法
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    //实体类使用requestbody包装
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工为:{}",emp);

        empService.update(emp);
        return Result.success();
    }

}
