package com.soullan.fakeojbe.controller;

import com.soullan.fakeojbe.modle.response.Result;
import com.soullan.fakeojbe.modle.vo.UserVo;
import com.soullan.fakeojbe.service.LoginService;
import com.soullan.fakeojbe.util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
public class LoginController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public Result login(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> params) throws UnsupportedEncodingException {
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);

        String username = params.get("username");
        String pwd = params.get("pwd");

        Map<String, Object> map = loginService.Login(username, pwd);

        logger.info("message: {}", map.get("message"));

        if ((Boolean) map.get("success")) return ResultUtil.cmdSuccess(map.get("errorCode").toString(), map.get("message").toString(), map.get("User"));
        else return ResultUtil.cmdFail(map.get("errorCode").toString(), map.get("message").toString(), map.get("User"));
    }

    @GetMapping("/loginStatue")
    @RequiresAuthentication
    public Result loginStatue() {
        UserVo userVo = loginService.statue();
        if (userVo == null) return ResultUtil.fail();
        return ResultUtil.dSuccess(userVo);
    }

    @GetMapping("logout")
    public Result logout(HttpServletResponse response) {
        loginService.Logout();
        return ResultUtil.success();
    }

}
