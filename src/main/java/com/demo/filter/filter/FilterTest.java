package com.demo.filter.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class FilterTest implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Log Filter :: init ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Log Filter :: doFilter 실행 ");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();

        // 요청의 추적을 위해 UUID 사용
        String uuid = UUID.randomUUID().toString();
        servletRequest.setAttribute("logId", uuid);

        try {
            log.info("Log Filter : doFilter : REQUEST [{}][{}]", uuid, requestURI);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("Log Filter : doFilter : RESPONSE [{}][{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {

        log.info("Log Filter :: destroy 실행 ");

    }
}
