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

    /**
     * 根据id查找员工信息
     * @param id
     * @return
     */
    public Employee getEmp(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    /**
     * 更新员工信息
     * @param employee
     */
    public void update(Employee employee) {
        //按照主键有选择的更新
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 根据Id删除员工
     * @param id
     */
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id批量删除
     * @param ids
     */
    public void deleteBatch(List<Integer> ids) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        //delete from XXX where emp_id IN (1,2,3)
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(employeeExample);
    }
}
