package com.soullan.fakeojbe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soullan.fakeojbe.dao.mapper.RoleMapper;
import com.soullan.fakeojbe.dao.mapper.UserMapper;
import com.soullan.fakeojbe.dao.mapper.UserRoleMapper;
import com.soullan.fakeojbe.modle.entity.PermissionEntity;
import com.soullan.fakeojbe.modle.entity.RoleEntity;
import com.soullan.fakeojbe.modle.entity.UserEntity;
import com.soullan.fakeojbe.modle.entity.UserRoleEntity;
import com.soullan.fakeojbe.service.RoleService;
import com.soullan.fakeojbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    private final RoleService roleService;

    @Autowired
    UserServiceImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public List<RoleEntity> getRoleList(UserEntity userEntity) {
        UserRoleEntity ur = new UserRoleEntity();
        ur.setUid(userEntity.getUserId());
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>(ur);
        List<UserRoleEntity> urList = userRoleMapper.selectList(queryWrapper);
        List<RoleEntity> roleList = new ArrayList<>();
        for (UserRoleEntity role : urList) {
            roleList.add(roleMapper.selectById(role.getRid()));
        }
        return roleList;
    }

    @Override
    public List<String> getPermission(UserEntity userEntity) {
        List<String> permissionEntityList = new ArrayList<>();
        for (RoleEntity role : getRoleList(userEntity)) {
            for(PermissionEntity per : roleService.getPermissionList(role))
                permissionEntityList.add(per.getPermission());
        }
        return permissionEntityList;
    }

    @Override
    public UserEntity getByName(String name) {
        LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserEntity::getUserName, name);
        return getOne(lambdaQueryWrapper);
    }
}
