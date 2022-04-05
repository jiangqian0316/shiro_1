package com.jqq.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

import java.security.Security;

public class TestAuthenticator {
    public static void main(String[] args) {
        //创建安全管理器对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //2.给安全管理器设置realm
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));

        //3.SecurityUtils全局工具类,给全局工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        //4.关键对象主体，subject主体
        Subject subject = SecurityUtils.getSubject();

        //创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("jiangqian", "123");

        try {
            System.out.println("认证状态:" + subject.isAuthenticated());
            subject.login(token);//用户认证
            System.out.println("认证状态:" + subject.isAuthenticated());
        } catch (IncorrectCredentialsException e) {
            System.out.println("认证失败：密码错误");
            e.printStackTrace();
        } catch (UnknownAccountException e){
            System.out.println("认证失败：账户不存在");
            e.printStackTrace();
        }

        Realm realm;
    }
}
