package com.jyp.greenhouse.service;

import com.jyp.greenhouse.core.entity.Admin;
import com.jyp.greenhouse.core.security.MD5;
import com.jyp.greenhouse.core.util.IdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by oplus on 2017/4/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/config/spring-base.xml")
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;
    @Test
    public void insert() {
        Admin admin = new Admin();
        admin.setAdminId("2");
        admin.setAdminName("jyp");
        admin.setAdminPassword(MD5.encode2hex(MD5.encode2hex("123456")+ MD5.DEFAULT_SALT));
        admin.setAdminState("-1");
        if (adminService.insert(admin) == 1)
            System.out.println("注册成功");
        else
            System.out.println("注册失败");
    }

    @Test
    public void login() {
        System.out.println(adminService.login("jyp","123456").getAdminName());
    }
}
