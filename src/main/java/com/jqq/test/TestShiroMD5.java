package com.jqq.test;

import org.apache.shiro.crypto.hash.Md5Hash;

public class TestShiroMD5 {
    public static void main(String[] args) {

        Md5Hash md5Hash = new Md5Hash("123");

        System.out.println(md5Hash);

        //使用MD5+salt处理
        Md5Hash md5Hash1 = new Md5Hash("123","zz");

        System.out.println(md5Hash1);

        //使用Md5 + salt + hash散列
        Md5Hash md5Hash3 = new Md5Hash("123", "zz", 1024);
        System.out.println(md5Hash3);
    }
}
