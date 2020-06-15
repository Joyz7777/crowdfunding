package com.jo.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jo.crowd.entity.Role;
import com.jo.crowd.entity.RoleExample;
import com.jo.crowd.mapper.RoleMapper;
import com.jo.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper rm;

    @Override
    public PageInfo<Role> getRolePage(Integer pageNum, Integer pageSize, String keyword) {


        // 开启分页功能
        PageHelper.startPage(pageNum,pageSize);

        //获取数据
        List<Role> roles = rm.selectByKeyword(keyword);

        PageInfo pageInfo = new PageInfo(roles);

        return pageInfo;
    }

    @Override
    public void saveRole(String roleName) {
        rm.insert(new Role(null,roleName));
    }

    @Override
    public void updateRole(Role role) {
        rm.updateByPrimaryKey(role);
    }

    @Override
    public void removeRoleByList(List<Integer> roleIdList) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andRoleIdIn(roleIdList);
        rm.deleteByExample(roleExample);

    }
}
