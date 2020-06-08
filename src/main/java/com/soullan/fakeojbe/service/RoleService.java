package com.soullan.fakeojbe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soullan.fakeojbe.modle.entity.PermissionEntity;
import com.soullan.fakeojbe.modle.entity.RoleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService extends IService<RoleEntity> {
    List<PermissionEntity> getPermissionList(RoleEntity roleEntity);
}
