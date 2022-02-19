package com.guojian.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @AUTHOR guojian
 * @create 2022-02-18-2022/2/18
 */
@SpringBootApplication
@MapperScan("com.guojian.server.mapper")
public class CloudofficeApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(CloudofficeApplication.class, args);
    }
}
