package com.jo.crowd.mvc.controller;


import com.jo.crowd.util.ResultEntity;
import com.jo.crowd.entity.Admin;
import com.jo.crowd.entity.Student;
import com.jo.crowd.service.api.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestController {


    @Autowired
    AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/test/exception.html", method = RequestMethod.GET)
    public ResultEntity<String> testResultEntity() {
        System.out.println("!1111");
        return ResultEntity.successWithData("你成功访问了/test/resultEntity.html");
    }

    @ResponseBody
    @RequestMapping(value = "/student/test.html")
    public String testStudent(@RequestBody Student student) {

        System.out.println(student.toString());
        return "student";
    }


    @RequestMapping("/test/ssm.html")
    public String testSSM(ModelMap modelMap) {

        List<Admin> all = adminService.getAll();
        modelMap.addAttribute("list", all);
        return "target";
    }


    @ResponseBody
    @RequestMapping(value = "/send/array/test.html", method = RequestMethod.POST)
    public String testAjax(@RequestBody List<Integer> arr, ModelMap modelMap) {

        for (Integer integer : arr) {
            System.out.println(integer);
        }
        return "success";
    }


}
