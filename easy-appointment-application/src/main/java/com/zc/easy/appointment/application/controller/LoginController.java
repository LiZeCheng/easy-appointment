package com.zc.easy.appointment.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizecheng
 * @date 2021/6/8 9:38
 */
@RestController
public class LoginController {
    @GetMapping("/hi")
    public String hi(){
        return "hello world";
    }
}
