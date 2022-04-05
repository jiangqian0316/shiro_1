package com.jqq.test;

import com.jqq.chiro.CustomerRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

public class TestCustomerRealmAuthenticator {
    public static void main(String[] args) {
        //创建securityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //设置自定义realm
        defaultSecurityManager.setRealm(new CustomerRealm());
        //将安全工具类设置成安全工具类
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //通过安全工具类获取Subject
        Subject subject = SecurityUtils.getSubject();
        //创建token
        UsernamePasswordToken token = new UsernamePasswordToken("jiangqian", "1231");

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

    }
}
