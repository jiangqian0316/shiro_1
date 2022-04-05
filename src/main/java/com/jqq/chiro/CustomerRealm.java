package com.jqq.chiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/*
* 自定义realm实现 将认证、授权数据的来源转换为数据库的实现
* */
public class CustomerRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名
        String principal = String.valueOf(token.getPrincipal());
        if ("jiangqian".equals(principal)){
            //参数1 返回数据库正确的用户名 //参数2:返回数据库的正确密码 //提供当前realm this.getName
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal,"123",this.getName());

            return simpleAuthenticationInfo;
        }
        return null;
    }
}
