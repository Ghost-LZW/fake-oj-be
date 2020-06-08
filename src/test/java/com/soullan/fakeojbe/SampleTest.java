package com.soullan.fakeojbe;

import com.soullan.fakeojbe.dao.mapper.PermissionMapper;
import com.soullan.fakeojbe.dao.mapper.RoleMapper;
import com.soullan.fakeojbe.dao.mapper.UserMapper;
import com.soullan.fakeojbe.modle.entity.PermissionEntity;
import com.soullan.fakeojbe.modle.entity.RoleEntity;
import com.soullan.fakeojbe.modle.entity.UserEntity;
import com.soullan.fakeojbe.service.RoleService;
import com.soullan.fakeojbe.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Test
    public void testSelect() throws NullPointerException {
        System.out.println(("----- select method test ------"));
        /*List<UserEntity> userList = userMapper.selectList(null);
        Assert.assertEquals(3, userList.size());
        userList.forEach(System.out::println);

        List<RoleEntity> roleList = roleMapper.selectList(null);
        roleList.forEach(System.out::println);

        List<PermissionEntity> perList = permissionMapper.selectList(null);
        perList.forEach(System.out::println);*/
        UserEntity user = userMapper.selectById(1);
        List<RoleEntity> roleList = userService.getRoleList(user);
        roleList.forEach(System.out::println);

        try {
            RoleEntity role = roleMapper.selectById(233);
            List<PermissionEntity> perList = roleService.getPermissionList(role);
            perList.forEach(System.out::println);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}