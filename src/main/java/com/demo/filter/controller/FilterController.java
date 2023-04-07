package com.demo.filter.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class FilterController {

    @GetMapping("/all-pass")
    public String allPass() {
        log.info("all-pass 호출 ");
        return "filterPassPage";
    }

    @GetMapping("/pass")
    public String pass(HttpServletRequest request) {
        // Log Filter에서 넣어준 UUID 꺼내와서 출력
        log.info("FilterController : REQUEST [{}][{}]", request.getAttribute("logId"), request.getRequestURI());
        return "filterPassPage";
    }

    @GetMapping("/chain")
    public String hello() {
        log.info("최형우님이 입장하셨습니다.");
        return "filterChain";
    }

}
