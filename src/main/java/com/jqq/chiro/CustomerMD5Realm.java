package com.jqq.chiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/*
* 自定义realm实现 将认证、授权数据的来源转换为数据库的实现
* */
public class CustomerMD5Realm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String primaryPrincipal = String.valueOf(principalCollection.getPrimaryPrincipal());
        System.out.println("身份信息:"+primaryPrincipal);

        //根据省份信息 用户名 获取当前的角色信息。以及权限信息爱 jiangqian admin user
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //将数据库的信息赋值给权限对象
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRole("user");
        //将数据库查询到的权限信息复制个权限对象
        simpleAuthorizationInfo.addStringPermission("user:*:*");
        return simpleAuthorizationInfo;


    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名
        String principal = String.valueOf(token.getPrincipal());

        if ("jiangqian".equals(principal)){

            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                    principal,                                               //参数1 返回数据库正确的用户名
                    "3b80952952670073109220e11423e3aa",     //参数2:数据库md5加密的密码+salt的密码
                    ByteSource.Util.bytes("zz"),                      //注册时的随机盐
                    this.getName());                                        //提供当前realm this.getName

            return simpleAuthenticationInfo;
        }
        return null;
    }
}
