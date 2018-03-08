package com.jyp.greenhouse.core.shiro;

import com.jyp.greenhouse.core.entity.Admin;
import com.jyp.greenhouse.core.entity.Adminroleadmin;
import com.jyp.greenhouse.core.plugin.ApplicationContextUtil;
import com.jyp.greenhouse.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oplsu on 2016/10/22.
 */
public class MyRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);
//    @Resource
//    AdminService adminService;


    /**
     * 为当前登录的Subject授予角色和权限
     *
     * 经测试:本例中该方法的调用时机为需授权资源被访问时
     * 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
     * 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
     * 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String currentUsername = (String) super.getAvailablePrincipal(principals);
        if (null != currentUsername) {
            AdminService adminService = ApplicationContextUtil.getBean(AdminService.class);
            Admin admin = adminService.getByName(currentUsername);
            if (null != admin.getAdminName()) {
                String rolestr = adminService.listAdminRoles(admin.getAdminId());
                List<String> roles = new ArrayList<>();
                SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
                if (admin.getAdminState().equals("-1") || admin.getAdminState().equals("0"))
                    simpleAuthorInfo.addStringPermission("admin");
                if (admin.getAdminState().equals("-1"))
                    roles.add("supermanage");
                String[] rolearr= Adminroleadmin.getRoles();
                for(String role:rolearr){
                    if(rolestr.contains(role))
                        roles.add(role);
                }
                if (roles.size() > 0)
                    simpleAuthorInfo.addRoles(roles);
                return simpleAuthorInfo;
            }
        }
        return null;
    }


    /**
     * 验证当前登录的Subject
     *
     * 本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = (String) token.getPrincipal();
        AdminService adminService = ApplicationContextUtil.getBean(AdminService.class);
        Admin admin = adminService.getByName(username);
        System.out.println(admin.getAdminName()+"/"+admin.getAdminPassword());
        if (admin.getAdminName() == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if (admin.getAdminState().equals("1"))
            throw new LockedAccountException("账户已锁定");
        setSession("admin", admin);
        return new SimpleAuthenticationInfo(
                admin.getAdminName(), //用户名
                admin.getAdminPassword(), //密码
                getName()  //realm name
        );
    }


    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     *
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */

    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }


    /**
     * 重新赋值权限(在比如:给一个角色临时添加一个权限,需要调用此方法刷新权限,否则还是没有刚赋值的权限)
     *
     * @param myRealm  自定义的realm
     * @param username 用户名
     */
    public static void reloadAuthorizing(MyRealm myRealm, String username) {

        //add by jizhun at 重新修改权限后清楚缓存，调用doGetAuthorizationInfo重新取角色的权限信息
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyRealm shiroRealm = (MyRealm) rsm.getRealms().iterator().next();
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        logger.info("update.user=" + username + ",login.user=" + SecurityUtils.getSubject().getPrincipal().toString());
        //shiroRealm.clearAllCachedAuthorizationInfo2();//清楚所有用户权限
        //第一个参数为用户名,第二个参数为realmName,test想要操作权限的用户
        SimplePrincipalCollection principals = new SimplePrincipalCollection(username, realmName);
        subject.runAs(principals);
        shiroRealm.getAuthorizationCache().remove(subject.getPrincipals());
        subject.releaseRunAs();
    }

    /**
     * 清空所有关联认证
     */
    public void clearAllCachedAuthorizationInfo2() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                System.out.println(key + ":" + key.toString());
                cache.remove(key);
            }
        }
    }
}
