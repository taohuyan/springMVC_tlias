package org.huyantao;

import org.huyantao.Mapper.DeptMapper;
import org.huyantao.pojo.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootwebItheimaApplicationTests {
    @Autowired
    private DeptMapper deptMapper;
    @Test
    public void Query_All() {
        List<Dept> deptList = deptMapper.list();
        if (deptList != null) {
            deptList.stream().forEach(dept -> {
                System.out.println(dept.toString());
            });
        }else{
            System.out.println("为空集！");
        }
    }
}
