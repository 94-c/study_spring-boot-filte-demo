package com.demo.filter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MyController {
    @GetMapping("/")
    public String hello() {
        log.info("최형우님이 입장하셨습니다.");
        return "Hello";
    }

}