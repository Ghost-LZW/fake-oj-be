package com.soullan.fakeojbe.controller;

import com.soullan.fakeojbe.modle.response.Result;
import com.soullan.fakeojbe.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
public class RegisterController extends BaseController {
    RegisterService registerService;

    @Autowired
    RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/ckName")
    @RequiresGuest
    public Integer ckName(HttpServletRequest request) {
        String name = request.getParameter("name");
        name = name.trim();
        return registerService.ckName(name);
    }

    @PostMapping("/register")
    @RequiresGuest
    public Result Register(HttpServletRequest request, @RequestBody Map<String, String> params) {
        String userName = params.get("username");
        log.info(userName);
        userName = userName.trim();
        String pwd = params.get("pwd");
        String email = params.get("email");
        return registerService.register(userName, pwd, email);
    }
}
