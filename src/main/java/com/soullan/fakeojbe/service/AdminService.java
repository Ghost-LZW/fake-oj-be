package com.soullan.fakeojbe.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soullan.fakeojbe.dao.mapper.UserRoleMapper;
import com.soullan.fakeojbe.modle.entity.UserEntity;
import com.soullan.fakeojbe.modle.entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends BaseService {
    private final UserService userService;
    private final UserRoleMapper userRoleMapper;
    private final RedisService redisService;

    @Autowired
    public AdminService(UserService userService, UserRoleMapper userRoleMapper,
                        RedisService redisService) {
        this.userService = userService;
        this.userRoleMapper = userRoleMapper;
        this.redisService = redisService;
    }

    public String lock(Integer id) {
        UserEntity user = userService.getById(id);
        user.setUserIsLocked(true);
        userService.updateById(user);
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUid(user.getUserId());
        QueryWrapper<UserRoleEntity> wrapper = new QueryWrapper<>(userRoleEntity);
        userRoleMapper.delete(wrapper);
        return "locked";
    }

    public String unlock(Integer id) {
        UserEntity user = userService.getById(id);
        user.setUserIsLocked(false);
        userService.updateById(user);
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUid(user.getUserId());
        userRoleEntity.setRid(1);
        userRoleMapper.insert(userRoleEntity);
        userRoleEntity.setRid(2);
        userRoleMapper.insert(userRoleEntity);
        return "unlock";
    }

    public String banAdd(Integer id) {
        UserEntity user = userService.getById(id);
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRid(2);
        userRoleEntity.setUid(id);
        QueryWrapper<UserRoleEntity> wrapper = new QueryWrapper<>(userRoleEntity);
        userRoleMapper.delete(wrapper);
        redisService.setVal(user, null);
        return "BanAdd";
    }

    public String liveAdd(Integer id) {
        UserEntity user = userService.getById(id);
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUid(id);
        userRoleEntity.setRid(2);
        userRoleMapper.insert(userRoleEntity);
        redisService.setVal(user, null);
        return "canAdd";
    }


}
