package com.soullan.fakeojbe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soullan.fakeojbe.modle.entity.RoleEntity;
import com.soullan.fakeojbe.modle.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends IService<UserEntity> {
    List<RoleEntity> getRoleList(UserEntity userEntity);
    List<String> getPermission(UserEntity userEntity);
    UserEntity getByName(String name);
}
