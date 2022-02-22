package com.guojian.server.controller;

import com.guojian.server.pojo.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guojian
 * @create 2022-02-19-2022/2/19
 */
@RestController
public class TestController
{
    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }

    @GetMapping("/employee/basic/hello")
    public String hello2()
    {
        return "/employee/basic/hello";
    }

    @GetMapping("/employee/advanced/hello")
    public String hello3()
    {
        return "/employee/advanced/hello";
    }
}
