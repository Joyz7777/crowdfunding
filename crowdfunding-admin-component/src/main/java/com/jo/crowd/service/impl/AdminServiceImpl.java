package com.jo.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jo.crowd.entity.Admin;
import com.jo.crowd.entity.AdminExample;
import com.jo.crowd.mapper.AdminMapper;
import com.jo.crowd.service.api.AdminService;
import com.jo.crowd.util.CrowdConstant;
import com.jo.crowd.util.CrowdUtil;
import com.jo.crowd.util.LoginFailedException;
import com.jo.crowd.util.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {


    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    AdminMapper adminMapper;


    public Admin getById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    public List<Admin> getAll() {
        return adminMapper.selectByExample(null);
    }

    public boolean saveAdmin(Admin admin) {

        int insert = adminMapper.insertSelective(admin);

        //System.out.println(1/0);

        return insert > 0;
    }

    public Admin doLogin(String loginAcct, String userPswd) {


        if ("".equals(loginAcct) || loginAcct == null || "".equals(userPswd) || userPswd==null){
            throw new LoginFailedException(CrowdConstant.MESSAGE_EMPTY_LOGINACCT_OR_PASSWORD);
        }

        Admin adminByLoginAcct = this.getAdminByLoginAcct(loginAcct);
        String output = CrowdUtil.md5(userPswd);
        boolean equals = Objects.equals(output, adminByLoginAcct.getUserPswd());
        if (!equals){

            throw new LoginFailedException(CrowdConstant.MESSAGE_INVALID_PASSWORD);
        }

        return adminByLoginAcct;
    }

    public Admin getAdminByLoginAcct(String loginAcct) {

        AdminExample adminExample = new AdminExample();

        // ②创建Criteria对象
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);


        List<Admin> admins = adminMapper.selectByExample(adminExample);


        //结果一个也没有
        if (admins == null || admins.size() == 0){
            throw new LoginFailedException(CrowdConstant.MESSAGE_NOT_FOUND_ADMIN_ACCOUNT);
        }

        //结果有多个
        if (admins.size()>1){
            throw new LoginFailedException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }


        return admins.get(0);
    }

    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {

        //开启分页插件
        PageHelper.startPage(pageNum,pageSize);

        List<Admin> list = adminMapper.selectAdminByKeyword(keyword);

        PageInfo<Admin> adminPageInfo = new PageInfo<>(list);

        return adminPageInfo;
    }

    @Override
    public void addAdmin(Admin admin) {
        String md5 = CrowdUtil.md5(admin.getUserPswd());
        admin.setUserPswd(md5);
        try {
            adminMapper.insert(admin);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
                throw new UserAlreadyExistsException("该账户已经存在");
            }
        }

    }

    @Override
    public void removeAdminById(Integer id) {
     adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateAdminByloginAcct(Admin admin) {

        AdminExample criteria = new AdminExample();
        criteria.createCriteria().andLoginAcctEqualTo(admin.getLoginAcct());

        try {
            adminMapper.updateByExampleSelective(admin,criteria);
        }catch (Exception e){
            logger.error(e.getClass().getName());
            e.printStackTrace();
        }


    }
}
