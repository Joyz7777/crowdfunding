package com.jo.crowd.mvc.controller;


import com.github.pagehelper.PageInfo;
import com.jo.crowd.entity.Admin;
import com.jo.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RequestMapping("admin")
@Controller
public class AdminController {


    @Autowired
    AdminService adminService;



    @RequestMapping("/do/update.html")
    public String updateAdminUser(Admin admin,
                                      @RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("keyword") String keyword){

        adminService.updateAdminByloginAcct(admin);

        return "redirect:/admin/get/user.html?pageNum="+pageNum+"&keyword="+keyword;


    }


    @RequestMapping("/to/edit/{id}.html")
    public String findAdminById(@PathVariable("id") Integer id,
                                ModelMap model){
        Admin adminById = adminService.getById(id);
        model.addAttribute("admin",adminById);
        return "admin-edit";


    }


    @RequestMapping("/remove/{id}/{pageNum}/{keyword}.html")
    public String removeAdmin(@PathVariable("id") Integer id,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("keyword") String keyword
                             ){

            adminService.removeAdminById(id);

            return "redirect:/admin/get/user.html?pageNum="+pageNum+"&keyword="+keyword;
    }


    @RequestMapping("/save.html")
    public String addAdmin(Admin admin){


        adminService.addAdmin(admin);

        return "redirect:/admin/get/user.html?pageNum="+Integer.MAX_VALUE;
    }


    public String removeUser(@RequestParam("id") Integer id){

        return  null;
    }

    @RequestMapping("/get/user.html")
    public ModelAndView getPageInfo(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "7") Integer pageSize,
                                    @RequestParam(value = "keyword",defaultValue = "") String keyword,
                                    ModelAndView modelAndView){

        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);

        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("admin-page");
        return  modelAndView;

    }


    @RequestMapping("/do/logout.html")
    public String doLogout(HttpSession session){
        session.removeAttribute("username");
        return "admin-login";
    }

    //@ResponseBody
    @RequestMapping(value = "/doLogin.html", method = RequestMethod.POST)
    public String doLogin(@RequestParam("loginAcct") String loginAcct,
                          @RequestParam("userPswd") String userPswd,
                          HttpSession session) {
        Admin admin = adminService.doLogin(loginAcct, userPswd);
        session.setAttribute("admin", admin);
        return "redirect:/admin/to/main/page.html";
    }


}
