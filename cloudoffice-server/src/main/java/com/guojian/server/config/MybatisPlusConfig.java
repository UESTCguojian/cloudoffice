/**
 * @projectName: cloudoffice
 * @package: com.guojian.server.config
 * @className: MybatisPlusConfig
 * @author: Substatham
 * @date: 2022/2/23 16:22
 * @version: 1.0
 */

package com.guojian.server.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
