package com.jqq.test;

import com.jqq.chiro.CustomerMD5Realm;
import com.jqq.chiro.CustomerRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

public class TestCustomerMD5RealmAuthenticator {
    public static void main(String[] args) {
        //创建securityManager安全管理器

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        CustomerMD5Realm customerMD5Realm = new CustomerMD5Realm();
        //设置realm凭证
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //使用的算法
        credentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数
        credentialsMatcher.setHashIterations(1024);
        customerMD5Realm.setCredentialsMatcher(credentialsMatcher);
        //设置自定义realm
        defaultSecurityManager.setRealm( customerMD5Realm);
        //将安全工具类设置成安全工具类
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //通过安全工具类获取Subject
        Subject subject = SecurityUtils.getSubject();
        //创建token
        UsernamePasswordToken token = new UsernamePasswordToken("jiangqian", "123");

        try {
            System.out.println("认证假状态:" + subject.isAuthenticated());
            subject.login(token);//用户认证
            System.out.println("认证真状态:" + subject.isAuthenticated());
        } catch (IncorrectCredentialsException e) {
            System.out.println("认证失败：密码错误");
            e.printStackTrace();
        } catch (UnknownAccountException e){
            System.out.println("认证失败：账户不存在");
            e.printStackTrace();
        }

        //认证用户进行授权
        if (subject.isAuthenticated()){   //判断认证状态
            // 基于角色权限控制
            System.out.println(subject.hasRole("super"));
            System.out.println("-----------");
            //基于多角色的权限控制
            System.out.println(subject.hasAllRoles(Arrays.asList("admin","user")));
            System.out.println("-----------");
            //是否具有其中一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "super", "user"));
            for (boolean aBoolean : booleans) {
                System.out.println(aBoolean);
            }

            //基于权限字符串的访问控制 资源标志符：操作：资源类型
            System.out.println("++++++++++++++++++++++++++++");
            System.out.println("权限"+subject.isPermitted("user:*:01"));

            //分别具有哪些权限
            boolean[] permitted = subject.isPermitted("user:*:*", "user:update:01", "order:*:*");
            for (boolean b : permitted) {
                System.out.println(b);
            }

            //同时具有哪些权限
            boolean permittedAll = subject.isPermittedAll("user:*:*", "order:*:*");
            System.out.println(permittedAll);
        }

    }
}
