package com.jo.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.jo.crowd.entity.Admin;

import java.util.List;


public interface AdminService {


    Admin getById(Integer id);

    List<Admin> getAll();

    boolean saveAdmin(Admin admin);

    Admin doLogin(String loginAcct, String userPswd);

    Admin getAdminByLoginAcct(String loginAcct);

    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);


    void addAdmin(Admin admin);

    void removeAdminById(Integer id);

    void updateAdminByloginAcct(Admin admin);
}
