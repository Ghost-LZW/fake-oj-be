package com.soullan.fakeojbe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soullan.fakeojbe.dao.mapper.PermissionMapper;
import com.soullan.fakeojbe.dao.mapper.RoleMapper;
import com.soullan.fakeojbe.dao.mapper.RolePermissionMapper;
import com.soullan.fakeojbe.modle.entity.PermissionEntity;
import com.soullan.fakeojbe.modle.entity.RoleEntity;
import com.soullan.fakeojbe.modle.entity.RolePermissionEntity;
import com.soullan.fakeojbe.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionEntity> getPermissionList(RoleEntity roleEntity) {
        RolePermissionEntity rp = new RolePermissionEntity();
        rp.setPid(roleEntity.getRoleID());
        QueryWrapper<RolePermissionEntity> queryWrapper = new QueryWrapper<>(rp);
        List<RolePermissionEntity> rolePermissionEntityList = rolePermissionMapper.selectList(queryWrapper);
        List<PermissionEntity> permissionList = new ArrayList<>();
        for (RolePermissionEntity permission : rolePermissionEntityList) {
            permissionList.add(permissionMapper.selectById(permission.getPid()));
        }
        return permissionList;
    }
}
