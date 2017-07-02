package com.xdShop.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

/**
 * Created by pro on 16/11/17.
 */
@Controller
public class TestHttpClient {
    @RequestMapping("/test/get")
    @ResponseBody
    public String test1(String name,int age) throws UnsupportedEncodingException {
        String s=new String(name.getBytes("iso-8859-1"),"utf-8");
        System.out.println(s);
        System.out.println(age);
        return name+age;
    }

    @RequestMapping("/test/post")
    @ResponseBody
    public String test2(String name,int age) throws UnsupportedEncodingException {
        String s=new String(name.getBytes("iso-8859-1"),"utf-8");
        System.out.println(s);
        System.out.println(age);
        return name+age;
    }

}
