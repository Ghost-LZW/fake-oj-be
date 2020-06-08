package com.soullan.fakeojbe.util;

import com.soullan.fakeojbe.modle.entity.UserEntity;
import com.soullan.fakeojbe.modle.vo.UserVo;
import com.soullan.fakeojbe.service.RedisService;
import com.soullan.fakeojbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserUtil extends BaseUtil {

    private final UserService userService;
    private final RedisService redisService;

    @Autowired
    public UserUtil(UserService userService, RedisService redisService) {
        this.redisService = redisService;
        this.userService = userService;
    }

    public Boolean canAdd(UserEntity user) {
        return redisService.getPermission(user).contains("PROBLEM:ADD");
    }

    public UserVo getUserInfo(String username) {
        UserEntity user = userService.getByName(username);
        List<String> permission = redisService.getPermission(user);
        Boolean admin = permission.contains("Admin");
        Boolean canAdd = permission.contains("PROBLEM:ADD");
        if (user.getUserIsAdmin() != admin) {
            user.setUserIsAdmin(admin);
            userService.updateById(user);
        }

        return new UserVo(user.getUserName(), user.getUserEmail(), user.getUserSubmitCount(),
                user.getUserSolvedCount(), admin, canAdd);
    }
}
