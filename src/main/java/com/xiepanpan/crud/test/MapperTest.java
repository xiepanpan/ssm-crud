package com.xiepanpan.crud.test;

import com.xiepanpan.crud.bean.Department;
import com.xiepanpan.crud.bean.Employee;
import com.xiepanpan.crud.dao.DepartmentMapper;
import com.xiepanpan.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * describe:
 * 使用spring的单元测试
 * 1.导入spring test jar包
 * 2.ContextConfiguration 指定配置文件
 * @author xiepanpan
 * @date 2018/10/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void testCRUD(){
        System.out.println(departmentMapper);
//        departmentMapper.insert(new Department(null,"开发部"));
//        departmentMapper.insert(new Department(null,"测试部"));

//        employeeMapper.insert(new Employee(null,"Jerry","M","Jerry@xiepanpan.com",1));

        //批量插入多个员工 使用可以执行批量操作的sqlSession
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000 ; i++) {
            String uuid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null,uuid,"M",uuid+"@xiepanpan.com",1));
        }
    }
}
