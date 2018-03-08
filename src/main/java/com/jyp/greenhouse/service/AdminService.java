package com.jyp.greenhouse.service;


import com.jyp.greenhouse.core.mapper.AdminMapper;
import com.jyp.greenhouse.core.mapper.AdminRoleMapper;
import com.jyp.greenhouse.core.entity.Admin;
import com.jyp.greenhouse.core.entity.Adminroleadmin;
import com.jyp.greenhouse.core.util.ServletUtil;
import com.jyp.greenhouse.core.util.TimeUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wjh on 2016/3/22.
 */
@Service
public class AdminService {


    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    /**
     * 添加管理员
     *
     * @param admin
     */
    public int insert(Admin admin) {
        return adminMapper.insert(admin);
    }


    /**
     * 列出所有管理员
     *
     * @return
     */
    public List<Admin> list() {
        List<Admin> admins = adminMapper.list();
        for (Admin admin : admins) {
            admin.setRoles(listAdminRoles(admin.getAdminId()));
        }
        return admins;
    }

    /**
     * 用户名和密码登陆验证
     *
     * @param name
     * @param pass
     * @return
     */
    public Admin login(String name, String pass) {
        return adminMapper.login(name, pass);
    }

    public Admin findById(String id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据管理员昵称获取管理员信息
     *
     * @param name
     * @return
     */
    public Admin getByName(String name) {
        return adminMapper.selectByName(name);
    }


    /**
     * 更新登陆时间和ip
     *
     * @param admin
     */
    public void updateLogininfo(Admin admin) {
        admin.setAdminPrelogintime(admin.getAdminLastlogintime());
        admin.setAdminPreloginip(admin.getAdminLastloginip());
        admin.setAdminLastlogintime(TimeUtil.getNowTimestamp()+"");
        admin.setAdminLastloginip(ServletUtil.getIp());
        adminMapper.updateLogininfo(admin);
    }

    /**
     * 获取管理员角色表
     *
     * @param id
     * @return
     */
    public String listAdminRoles(String id) {
        String roles = "";
        List<Adminroleadmin> adminroleadmins = adminRoleMapper.list(id);
        Set<Adminroleadmin> roleset = new HashSet<>(adminroleadmins);
        int count = roleset.size();
        if (count > 0) {
            for (Adminroleadmin role : roleset) {
                roles += role.getRole() + ",";
            }
        }
        if (count > 0)
            roles = roles.substring(0, roles.length() - 1);
        return roles;
    }

    /**
     * 更新管理员角色
     *
     * @param oldroles 旧角色
     * @param newroles 新角色
     * @param adminid
     * @return
     */
    @Transactional
    public boolean setRole(String[] oldroles, String[] newroles, String adminid) {
        if (oldroles != null)
            for (String roleold : oldroles) {
                if (adminRoleMapper.delete(adminid, roleold) != 1) {
                    throw new RuntimeException();
                }
            }
        if (newroles != null) {
            for (String rolenew : newroles) {
                if (adminRoleMapper.add(adminid, rolenew) != 1)
                    throw new RuntimeException();
            }
        }
        return true;
    }

    /**
     * 更新管理员信息
     *
     * @param admin
     * @return
     */
    public int updateSelective(Admin admin) {
        return adminMapper.update(admin);
    }

    /**
     * 删除管理员
     *
     * @param adminid
     */
    @Transactional
    public void delAdmin(String adminid) {
        if (adminMapper.deleteByPrimaryKey(adminid) == 1) {
            delAdminRole(adminid);
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * 删除管理员角色
     *
     * @param adminid
     * @return
     */
    @Transactional
    public int delAdminRole(String adminid) {
        return adminRoleMapper.deleteByAdminid(adminid);
    }


}
