package com.jqq.chiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.security.Security;

/**
 *使用自动以realm
  */

class CustomerRealmTest {

    @Test
    public void TestCustomerRealmAuthenticator(){
        //创建securityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //设置自定义realm
        defaultSecurityManager.setRealm(new CustomerRealm());
        //将安全工具类设置成安全工具类
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //通过安全工具类获取Subject
        Subject subject = SecurityUtils.getSubject();
        //创建token
        UsernamePasswordToken token = new UsernamePasswordToken("jiangqian", "123");

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

    }

}