package org.huyantao.Controller;

import lombok.extern.slf4j.Slf4j;
import org.huyantao.Service.DeptService;
import org.huyantao.pojo.Dept;
import org.huyantao.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/depts") //公共部分的路径抽取出来
public class DeptController {

    @Autowired
    private DeptService deptService;

    //使用logger实现日志输出 可以使用注解@sjf4j
    //private static Logger log = LoggerFactory.getLogger(DeptController.class);

    /*
    查询部门数据
    @return
     */
    //复杂 所以使用了新的注解@GetMapping
    //@RequestMapping(value = "/depts",method = RequestMethod.GET) //指定请求方式为get
    @GetMapping
    public Result list(){
        //调用log的info方法输出日志
        log.info("查询全部部门的数据");
        //System.out.println("查询全部输出信息");

        //调用service查询部门数据
        List<Dept> deptList = deptService.list();
        //
        return Result.success(deptList);
    }

    /*
    删除部门数据
    @return
     */
    @DeleteMapping("/{id}")
    //声明变量接收路径参数Integer 以及接收路径参数的注解Pathvariable
    public Result delete(@PathVariable Integer id) throws Exception {
        log.info("根据id删除部门:{}",id);
        //调用service中的方法来删除部门
        deptService.delete(id); //不需要返回值 无参的result方法
        return Result.success();
    }

    /*
    增加部门
     */
    @PostMapping
    //接收数据部门名称
    public Result add(@RequestBody Dept dept){
        log.info("新增部门:{}",dept);
        //调用service中的方法增加部门
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询部门id:{}",id);
        //调用service中的查询方法 这里需要返回
        Dept dept= deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门为:{}",dept);
        // //调用service中的修改方法 不需要返回
        deptService.update(dept);
        return  Result.success();
    }


}
