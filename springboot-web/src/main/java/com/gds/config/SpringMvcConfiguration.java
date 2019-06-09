package com.gds.config;

import com.gds.compoent.UrlLocaleResolver;
import com.gds.compoent.interceptor.LoginHandlerInterceptor;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 扩展SpringMvc的功能

/**
 * 编写一个配置类（@Configuration），是WebMvcConfigurerAdapter类型；
 * 不能标注@EnableWebMvc, 所有的配置类都会一起起作用
 * 加上@EnableWebMvc 全面接管
 */
//@EnableWebMvc
@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/atguigu").setViewName("success");
    }

    // 所有的WebMvcConfigurer组件会一起起作用
    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {

            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                        .excludePathPatterns("/index.html", "/", "/user/login", "/webjars/**", "/asserts/**");
                // springboot已经做好static的映射， 所以拦截器不需要exclude
            }
        };
        return webMvcConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver () {
        return new UrlLocaleResolver();
    }

}
