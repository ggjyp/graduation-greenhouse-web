package com.jyp.greenhouse.web.backend.controller;

import com.jyp.greenhouse.core.entity.*;
import com.jyp.greenhouse.core.security.MD5;
import com.jyp.greenhouse.core.shiro.MyRealm;
import com.jyp.greenhouse.core.util.*;
import com.jyp.greenhouse.service.*;
import com.jyp.greenhouse.web.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by wjh on 2016/3/21.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);
    @Autowired
    private AdminService adminService;

    
    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView mav = new ModelAndView();
        try {
//            系统信息
            mav.addObject("osInfo", OSInfo.getCurrentOSInfo());
        } catch (Exception ex) {
            ex.printStackTrace();
            mav.addObject(Constants.MSG, Constants.SYSERROR);
            mav.addObject(Constants.MSG_DETAIL, Constants.SYSERROR_DETAIL);
            mav.setViewName("systemerror");
        }
        mav.setViewName("admin/home");
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginForm() {
        ModelAndView mav = new ModelAndView("admin/login");
        return mav;
    }

//    @ResponseBody
//    @RequestMapping(value = "/unlock", method = RequestMethod.POST)
//    public HashMap unlock(@RequestParam String pass) {
//        HashMap hm = new HashMap();
//        if (StringUtil.isBlank(pass)) {
//            hm.put(Constants.MSG, Constants.PASSEERROR);
//            return hm;
//        }
//        String adminid = String.valueOf(ServletUtil.getSession(Constants.SESSION_ADMIN));
//        if (StringUtil.isBlank(adminid)) {
//            hm.put(Constants.MSG, "会话超时，请重新登录");
//        } else {
//            try {
//                Admin admin = adminService.findById(adminid);
//                if (admin != null && admin.getAdminPassword().equals(MD5.encode2hex(pass+MD5.DEFAULT_SALT)))
//                    hm.put(Constants.STATE, Constants.SUCC);
//                else
//                    hm.put(Constants.MSG, Constants.PASSEERROR);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                hm.put(Constants.MSG, Constants.SYSERROR);
//            }
//        }
//
//        return hm;
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();

        ServletUtil.setRequest(Constants.RECORD_CONTENT, "登陆操作");

        UsernamePasswordToken token = new UsernamePasswordToken(username, password.trim()+ MD5.DEFAULT_SALT);
        token.setRememberMe(false);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        HttpSession session = request.getSession();
        int n = 0;
        String msg = "";
        String jspname = "";
        try {
            currentUser.login(token);
            //验证是否登录成功
            if (currentUser.isAuthenticated()) {
//                更新登陆信息
                Admin admin = (Admin) session.getAttribute("admin");
                session.setAttribute(Constants.SESSION_ADMIN, admin.getAdminId());
                session.setAttribute("admin_name", admin.getAdminName());
                session.setAttribute("super", admin.getAdminState());
                session.setAttribute("adminimage", admin.getAdminImage());

                String roles = adminService.listAdminRoles(admin.getAdminId());
                session.setAttribute("roles", roles);

                mav.addObject(Constants.MSG, "登入成功");
                mav.addObject("detail", "3秒内跳转到管理页面");
                mav.addObject("url", "/admin/index.do");
                mav.addObject("redtext", "success");

                adminService.updateLogininfo(admin);

                session.setAttribute("admin", null);

            } else {
                token.clear();
                session.invalidate();
                mav.addObject(Constants.MSG, "登入失败");
                mav.addObject("detail", "3秒内回到登入页面");
                mav.addObject("url", "/admin/logout.do");
                mav.addObject("redtext", "fail");

            }
            mav.addObject("time", "3000");
            mav.setViewName("jsalert");
            n++;
        } catch (UnknownAccountException uae) {
            msg = "账户不存在";
            jspname = "jsalert";
        } catch (IncorrectCredentialsException ice) {
            msg = "密码不正确";
            jspname = "jsalert";
        } catch (LockedAccountException lae) {
            msg = "账户已锁定";
            jspname = "jsalert";
        } catch (ExcessiveAttemptsException eae) {
            msg = "用户名或密码错误次数过多";
            jspname = "jsalert";
        } catch (AuthenticationException ae) {
            msg = "用户名或密码不正确";
            jspname = "jsalert";
        } catch (Exception ex) {
            ex.printStackTrace();
            msg = Constants.SYSERROR;
            jspname = "systemerror";
        }
        if (n == 0) {

            mav.addObject("detail", "3秒内回到登入页面");
            mav.addObject("url", "/admin/logout.do");
            mav.addObject("time", "3000");
            mav.addObject("redtext", "fail");
            mav.addObject(Constants.MSG, msg);
            mav.setViewName(jspname);
        }

        return mav;
    }

    @RequestMapping(value = "/logout")
    public ModelAndView outlogin(HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("admin/login");
        Subject currentUser = SecurityUtils.getSubject();
        if (SecurityUtils.getSubject().getSession() != null) {
            currentUser.logout();
        }
        return mav;
    }


    @RequestMapping(value = "/notpermit")
    public ModelAndView notpermit(ModelAndView mav) {
        mav.setViewName("notpermit");
        return mav;
    }

    @RequestMapping(value = "/list")
    public ModelAndView listadmin(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();

        ServletUtil.setRequest(Constants.RECORD_CONTENT, "列出管理员");

        try {
            mav.setViewName("admin/user/list");
            mav.addObject("list", adminService.list());
            mav.addObject("rolelist", Adminroleadmin.getRoles());
            mav.addObject("selfid", (String) request.getSession().getAttribute(Constants.SESSION_ADMIN));
        } catch (Exception ex) {
            ex.printStackTrace();
            mav.addObject(Constants.MSG, Constants.SYSERROR);
            mav.addObject(Constants.MSG_DETAIL, Constants.SYSERROR_DETAIL);
            mav.setViewName("systemerror");
        }
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public HashMap altAdmin(HttpServletRequest request, @RequestParam(required = false) MultipartFile image) {
        String data = request.getParameter("data");
        String type = request.getParameter("type");//0:修改密码1：修改昵称2：修改头像
        String id = request.getParameter("id");
        String option = "";
        switch (type) {
            case "0":
                option = "修改密码";
                break;
            case "1":
                option = "修改昵称";
                break;
            default:
                option = "修改头像";
        }

        ServletUtil.setRequest(Constants.RECORD_CONTENT, option);

        HashMap hm = new HashMap();
        int state = 0;
        String filepath = null;
        if (type.equals("0") || type.equals("1"))
            if (data == null || data.equals(""))
                state = 1;
        if (id == null || id.equals("")) {
            id = (String) request.getSession().getAttribute(Constants.SESSION_ADMIN);
            if (id == null || id.equals(""))
                state = 1;
        }

        if (type.equals("2"))
            if (image == null) {
                hm.put(Constants.MSG, "请选择要上传的图片");
                return hm;
            } else {
                try {
                    filepath = FileUtil.uploadfile(FileUtil.getuploaddir("admin/headimg"), image, request);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    hm.put(Constants.MSG, Constants.UPLOADFAIL);
                    return hm;
                }
                if (filepath == null) {
                    hm.put(Constants.MSG, Constants.UPLOADFAIL);
                    return hm;
                }
            }


        if (state == 0) {
            try {
                Admin admin = new Admin();
                admin.setAdminId(id);
                switch (type) {
                    case "0":
                        admin.setAdminPassword(MD5.encode2hex(data+ MD5.DEFAULT_SALT));
                        break;
                    case "1":
                        admin.setAdminName(data);
                        break;
                    default:
                        admin.setAdminImage(filepath);
                }
                if (adminService.updateSelective(admin) == 1) {
                    HttpSession session = request.getSession();
                    if (type.equals("1")) {
                        session.setAttribute("admin_name", data);
                    } else if (type.equals("2")) {
                        session.setAttribute("adminimage", filepath);
                    }
                    hm.put(Constants.STATE, Constants.SUCC);
                } else {
                    hm.put(Constants.MSG, Constants.UPDATEERROR);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                hm.put(Constants.MSG, Constants.SYSERROR);
            }
        } else {
            hm.put(Constants.MSG, "参数不合法");
        }
        return hm;
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public HashMap addAdmin(HttpServletRequest request, HttpServletResponse response) {
        String nickname = request.getParameter("name");
        String password = request.getParameter("password");
        String state = request.getParameter("state");

        HashMap hm = new HashMap();
        String msg = "";
        if (nickname == null || nickname.equals("")) {
            msg = "昵称不能为空";
        }
        if (password == null || password.equals("")) {
            msg = "密码不能为空";
        }
        if (state == null || state.equals("")) {
            state = "0";
        }

        if (msg.equals("")) {
            try {
                Admin admin = new Admin();
                admin.setAdminId(IdGenerator.uuid19());
                admin.setAdminName(nickname);
                admin.setAdminPassword(MD5.encode2hex(password+ MD5.DEFAULT_SALT));
                admin.setAdminState(state);
                if (adminService.insert(admin) == 1) {
                    hm.put(Constants.STATE, Constants.SUCC);
                } else {
                    hm.put(Constants.MSG, Constants.UPDATEERROR);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                hm.put(Constants.MSG, Constants.SYSERROR);
            }
        } else {
            hm.put(Constants.MSG, msg);
        }
        return hm;
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public HashMap delAdmin(HttpServletRequest request, HttpServletResponse response) {
        HashMap hm = new HashMap();
        String id = request.getParameter("id");
        try {
            adminService.delAdmin(id);
            hm.put(Constants.STATE, "00000");
        } catch (Exception ex) {
            ex.printStackTrace();
            hm.put(Constants.MSG, Constants.SYSERROR);
        }
        return hm;
    }

    @ResponseBody
    @RequestMapping(value = "/role/set", method = RequestMethod.POST)
    public HashMap set_role(
            @RequestParam String adminname,
            @RequestParam String adminid,
            @RequestParam String oldrole,
            @RequestParam String newrole
    ) {
        HashMap hm = new HashMap();
        String[] oldroles = null;
        String[] newroles = null;
        if (!StringUtil.isBlank(oldrole))
            oldroles = oldrole.split(",");
        if (!StringUtil.isBlank(newrole))
            newroles = newrole.split(",");
        try {
            if (newroles == null || adminService.setRole(oldroles, newroles, adminid)) {
                MyRealm.reloadAuthorizing(new MyRealm(), adminname);
                hm.put(Constants.STATE, Constants.SUCC);
            } else {
                hm.put(Constants.MSG, Constants.UPDATEERROR);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            hm.put(Constants.MSG, Constants.SYSERROR);
        }
        return hm;
    }



}
