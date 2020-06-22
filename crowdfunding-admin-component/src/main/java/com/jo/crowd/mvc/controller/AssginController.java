package com.jo.crowd.mvc.controller;


import com.jo.crowd.entity.Role;
import com.jo.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/assign")
public class AssginController {

    @Autowired
    RoleService rs;




    @RequestMapping("/do/assign.html")
    public String doAssign(@RequestParam("pageNum") Integer pageNum,
                           @RequestParam("keyword") String keyword,
                           @RequestParam("adminId") Integer adminId,
                           @RequestParam(value = "roleIdList",required = false) List<Integer> roleIdList) {

            rs.assignRoleToAdmin(adminId,roleIdList);

        return "redirect:/admin/get/user.html?pageNum="+pageNum+"&keyword="+keyword;
    }


    @RequestMapping("/to/assignRole/page.html")
    public String toAssignPage(@RequestParam("adminId") Integer adminId,
                               ModelMap modelMap) {

        List<Role> assignedRole = rs.findAssignedRole(adminId);
        List<Role> unAssignedRole = rs.findUnAssignedRole(adminId);

        //装进modelMap
        modelMap.addAttribute("assignedRole", assignedRole);
        modelMap.addAttribute("unAssignedRole", unAssignedRole);

        return "assign-page";

    }
}
