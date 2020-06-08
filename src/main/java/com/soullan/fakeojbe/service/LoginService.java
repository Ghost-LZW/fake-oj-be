package com.soullan.fakeojbe.service;

import com.soullan.fakeojbe.modle.entity.UserEntity;
import com.soullan.fakeojbe.modle.vo.UserVo;
import com.soullan.fakeojbe.util.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService extends BaseService {

    private final UserUtil userUtil;
    private final UserService userService;


    @Autowired
    public LoginService(UserUtil userUtil, UserService userService) {
        this.userService = userService;
        this.userUtil = userUtil;
    }

    public Map<String, Object> Login(String userName, String pwd) {
        Map<String, Object> map = new HashMap<>();
        boolean success = false;
        String msg = "";
        String errorCode = "200";
        UserVo userVo = null;

        if (UserUtil.isEmpty(userName)) {
            msg = "用户名不能未空";
            errorCode = "400";
        } else if (UserUtil.isEmpty(pwd)) {
            msg = "密码不能未空";
            errorCode = "400";
        } else {
            Subject subject = SecurityUtils.getSubject();

            if (subject.isAuthenticated() && ((UserEntity) subject.getPrincipal()).getUserName().equals(userName)) {
                success = true;
            } else {
                UsernamePasswordToken token = new UsernamePasswordToken(userName, pwd);
                try {
                    subject.login(token);
                    if (subject.isAuthenticated()) success = true;
                } catch (UnknownAccountException e) {
					msg = "找不到账户！";
					errorCode = e.toString();
				} catch (IncorrectCredentialsException e) {
					msg = "密码不匹配！";
					errorCode = e.toString();
				} catch (LockedAccountException e) {
					msg = "账户被锁定！";
					errorCode = e.toString();
				} catch (AuthenticationException e) {
					msg = "身份验证失败！";
					errorCode = e.toString();
				}
            }

            if (success) {
                msg = "登录成功!";
                userVo = userUtil.getUserInfo(userName);
            }
        }

        UserEntity user = userService.getByName(userName);
        if (user.getUserIsLocked()) {
            success = false;
            msg = "您已被封禁，请咨询管理员";
            errorCode = "500";
            userVo = null;
        }

        map.put("success", success);
        map.put("message", msg);
        map.put("errorCode", errorCode);
        map.put("User", userVo);

        return map;
    }

    public UserVo statue() {
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        if (user == null) return null;
        return userUtil.getUserInfo(user.getUserName());
    }

    public void Logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
