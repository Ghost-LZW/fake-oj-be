package com.soullan.fakeojbe.config.realm;

import com.soullan.fakeojbe.modle.entity.PermissionEntity;
import com.soullan.fakeojbe.modle.entity.RoleEntity;
import com.soullan.fakeojbe.modle.entity.UserEntity;
import com.soullan.fakeojbe.service.RoleService;
import com.soullan.fakeojbe.service.UserService;
import com.soullan.fakeojbe.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

@Slf4j
public class FakeRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("检查用户配置");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserEntity userInfo = (UserEntity) principals.getPrimaryPrincipal();
        try {
            for (RoleEntity role : userService.getRoleList(userInfo)) {
                authorizationInfo.addRole(role.getRoleName());
                for (PermissionEntity p : roleService.getPermissionList(role)) {
                    authorizationInfo.addStringPermission(p.getPermission());
                }
            }
        } catch (Exception e) {
            throw new UnknownAccountException();
        }

        return authorizationInfo;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("开始验证");
        String username = (String) token.getPrincipal();

        UserEntity user;
        user = userService.getByName(username);

        if (UserUtil.isEmpty(user)) throw new UnknownAccountException();


        if (user == null) return null;

        return new SimpleAuthenticationInfo(
                user,
                user.getUserPwd(),
                getName()
        );
    }
}
