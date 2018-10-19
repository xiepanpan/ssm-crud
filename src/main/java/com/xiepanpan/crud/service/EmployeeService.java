package com.xiepanpan.crud.service;

import com.xiepanpan.crud.bean.Employee;
import com.xiepanpan.crud.bean.EmployeeExample;
import com.xiepanpan.crud.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe:
 *
 * @author xiepanpan
 * @date 2018/10/16
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    /**
     * 保存员工
     * @param employee
     */
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * 校验用户是否可用
     * @param empName
     * @return true 表示姓名可用 false表示不可用
     */
    public boolean checkUser(String empName) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        //拼装条件 查询是否有等于empName变量值的员工
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(employeeExample);
        return count==0;
    }
}
