package com.soullan.fakeojbe.service;

import com.soullan.fakeojbe.dao.mapper.UserRoleMapper;
import com.soullan.fakeojbe.modle.entity.UserEntity;
import com.soullan.fakeojbe.modle.entity.UserRoleEntity;
import com.soullan.fakeojbe.modle.response.Result;
import com.soullan.fakeojbe.util.ResultUtil;
import com.soullan.fakeojbe.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService extends BaseService {

    private final UserService userService;
    private final UserUtil userUtil;
    private final UserRoleMapper userRoleMapper;

    @Autowired
    public RegisterService(UserService userService, UserUtil userUtil,
                           UserRoleMapper userRoleMapper) {
        this.userService = userService;
        this.userUtil = userUtil;
        this.userRoleMapper = userRoleMapper;
    }

    public Integer ckName(String name) {
        if (userService.getByName(name) == null)
            return 1;
        else return 0;
    }

    public Result register(String userName, String pwd, String email) {
        String message = "";
        UserEntity user = null;
        boolean status = false;
        String errorCode = "";
        if (userService.getByName(userName) != null) {
            message = "用户名已存在";
        } else if (UserUtil.isEmpty(userName)) {
            message = "用户名不能未空";
            errorCode = "400";
        } else if (UserUtil.isEmpty(pwd)) {
            message = "密码不能未空";
            errorCode = "400";
        } else {
            user = new UserEntity();
            user.setUserName(userName);
            user.setUserPwd(pwd);
            user.setUserEmail(email);
            userService.save(user);
            user = userService.getByName(userName);
            UserRoleEntity ur = new UserRoleEntity();
            ur.setUid(user.getUserId());
            ur.setRid(1);
            userRoleMapper.insert(ur);
            ur.setRid(2);
            userRoleMapper.insert(ur);
            ur.setRid(3);
            userRoleMapper.insert(ur);
            message = "创建成功";
            status = true;
        }
        if (status) return ResultUtil.cmdSuccess(errorCode, message, userUtil.getUserInfo(userName));
        else return ResultUtil.cmFail(errorCode, message);
    }

}
