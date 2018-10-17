package com.xiepanpan.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiepanpan.crud.bean.Employee;
import com.xiepanpan.crud.bean.Msg;
import com.xiepanpan.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * describe:
 *
 * @author xiepanpan
 * @date 2018/10/16
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,5);
        //startPage下面紧跟着一个查询就是分页查询
        List<Employee> emps = employeeService.getAll();
        //连续显示多少页
        PageInfo pageInfo = new PageInfo(emps,5);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 查询所有
     * @param pn 页码
     * @return
     */
//    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model){
        PageHelper.startPage(pn,5);
        //startPage下面紧跟着一个查询就是分页查询
        List<Employee> emps = employeeService.getAll();
        //连续显示多少页
        PageInfo pageInfo = new PageInfo(emps,5);
        model.addAttribute("pageInfo",pageInfo);
        //视图解析器 /WEB-INF/views/
        return "list";
    }

}
