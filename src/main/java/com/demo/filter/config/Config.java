package com.demo.filter.config;

import com.demo.filter.filter.FilterTest;
import com.demo.filter.filter.FirstFilter;
import com.demo.filter.filter.SecondFilter;
import com.demo.filter.interceptor.InterceptorTest;
import jakarta.servlet.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Component
public class Config implements WebMvcConfigurer {


    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();

        // 구현한 필터 등록
        filterFilterRegistrationBean.setFilter(new FilterTest());

        // 필터 순서 지정
        filterFilterRegistrationBean.setOrder(3);

        // 필터가 적용될 URL 지정
        filterFilterRegistrationBean.addUrlPatterns("/filter-test/pass");

        return filterFilterRegistrationBean;
    }

    // 필터 체인 예제
    @Bean
    public FilterRegistrationBean firstFilterRegister() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new FirstFilter());
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean secondFilterRegister() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new SecondFilter());
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorTest())
                .order(1)
                .addPathPatterns("/interceptor-test/pass")
                .excludePathPatterns("/interceptor-test/all-pass");
    }
}
