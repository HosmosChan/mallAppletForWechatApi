package com.lettuce.management.config;

import com.lettuce.common.utils.table.PageTableArgumentResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.List;

/**
 * Code is far away from bug with the animal protected
 * 　┏┓　　  ┏┓
 * ┏┻┻━━━┻┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┣┛
 * 　　┗┳┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author Hosmos
 * @description WebMVC配置
 * @date 2021年07月08日
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * @return WebMvcConfigurer
     * @description 跨域支持
     * @author Hosmos
     * @date 2021-07-08
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }

    /**
     * @return PageTableArgumentResolver
     * @description datatable分页解析
     * @author Hosmos
     * @date 2021-07-08
     */
    @Bean
    public PageTableArgumentResolver tableHandlerMethodArgumentResolver() {
        return new PageTableArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(tableHandlerMethodArgumentResolver());
    }

    /**
     * 上传文件根路径
     */
    @Value("${files.path}")
    private String filesPath;

    /**
     * @param registry
     * @return void
     * @description 外部文件访问
     * @author Hosmos
     * @date 2021-07-08
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations(ResourceUtils.FILE_URL_PREFIX + filesPath + File.separator);
    }
}