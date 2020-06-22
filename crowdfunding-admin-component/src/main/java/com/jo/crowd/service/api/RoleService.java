package com.jo.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.jo.crowd.entity.Admin;
import com.jo.crowd.entity.Role;

import java.util.List;

public interface RoleService {

   PageInfo<Role> getRolePage(Integer pageNum, Integer pageSize, String keyword);

    void saveRole(String roleName);

    void updateRole(Role role);

    void removeRoleByList(List<Integer> roleIdList);

    List<Role> findAssignedRole(Integer admin);

    List<Role> findUnAssignedRole(Integer admin);

    void assignRoleToAdmin(Integer adminId, List<Integer> roleIdList);
}
