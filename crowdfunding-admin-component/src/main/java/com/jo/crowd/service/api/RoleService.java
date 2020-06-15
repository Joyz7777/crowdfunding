package com.jo.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.jo.crowd.entity.Role;

import java.util.List;

public interface RoleService {

   PageInfo<Role> getRolePage(Integer pageNum, Integer pageSize, String keyword);

    void saveRole(String roleName);

    void updateRole(Role role);

    void removeRoleByList(List<Integer> roleIdList);
}
