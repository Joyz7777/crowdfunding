package com.jo.crowd.mvc.controller;


import com.github.pagehelper.PageInfo;
import com.jo.crowd.entity.Admin;
import com.jo.crowd.entity.Role;
import com.jo.crowd.service.api.RoleService;
import com.jo.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {


    @Autowired
    RoleService rs;







    @ResponseBody
    @RequestMapping("/remove/array.json")
    public ResultEntity<String> removeByIdList(@RequestBody List<Integer> roleIdList){

        try {
            rs.removeRoleByList(roleIdList);
        }catch (Exception e){
            String exceptionName = e.getClass().getSimpleName();
            return  new ResultEntity<String>("FAILED",exceptionName,null);
        }

        return ResultEntity.successWithNoData();
    }




    @ResponseBody
    @RequestMapping(value = "/update.json",method = RequestMethod.POST)
    public ResultEntity<String> updateRole(Role role){

                try {
                    rs.updateRole(role);
                }catch (Exception e){
                    String exceptionName = e.getClass().getSimpleName();
                    return  new ResultEntity<String>("FAILED",exceptionName,null);
                }

        return ResultEntity.successWithNoData();
    }



    @ResponseBody
    @RequestMapping(value = "/save.json",method = RequestMethod.POST)
    public ResultEntity<String> saveRole(@RequestParam("roleName") String roleName){
                try {
                    rs.saveRole(roleName);
                }catch(Exception e){
                    String exceptionName = e.getClass().getName();
                    return  new ResultEntity<String>("FAILED",exceptionName,null);
                }

            return ResultEntity.successWithNoData();
    }


    @ResponseBody
    @RequestMapping(value = "/get/page.json",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public  ResultEntity<PageInfo<Role>> getRolePageInfo(@RequestParam("pageNum") Integer pageNum,
                                                              @RequestParam("pageSize") Integer pageSize,
                                                              @RequestParam("keyword") String keyword){


        PageInfo<Role> rolePage = rs.getRolePage(pageNum, pageSize,keyword);
        ResultEntity<PageInfo<Role>> pageInfoResultEntity = ResultEntity.successWithData(rolePage);
        // TODO: 2020/6/7 整理 统一返回结果 
        // TODO: 2020/6/7 是否需要捕获异常
        
        return pageInfoResultEntity;


    }

}
