package com.soullan.fakeojbe.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soullan.fakeojbe.dao.mapper.ProblemMapper;
import com.soullan.fakeojbe.dao.mapper.StatusMapper;
import com.soullan.fakeojbe.dao.mapper.UserMapper;
import com.soullan.fakeojbe.dao.mapper.UserRoleMapper;
import com.soullan.fakeojbe.modle.entity.SubmitStatusEntity;
import com.soullan.fakeojbe.modle.entity.UserEntity;
import com.soullan.fakeojbe.modle.entity.UserRoleEntity;
import com.soullan.fakeojbe.modle.vo.SolutionVo;
import com.soullan.fakeojbe.modle.vo.StatusPageInfoVo;
import com.soullan.fakeojbe.modle.vo.StatusVo;
import com.soullan.fakeojbe.util.StatusUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatusService extends BaseService {

    @Resource
    StatusMapper statusMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    ProblemMapper problemMapper;

    @Resource
    UserRoleMapper userRoleMapper;


    private final RedisService redisService;
    private final RoleService roleService;

    @Autowired
    StatusService(RedisService redisService, RoleService roleService) {
        this.redisService = redisService;
        this.roleService = roleService;
    }

    public StatusPageInfoVo getInfo() {
        return new StatusPageInfoVo(statusMapper.selectCount(null));
    }

    public List<StatusVo> getPage(Integer index, Integer size) {
        IPage<SubmitStatusEntity> page = new Page<>(index, size);
        page = statusMapper.selectPage(page, new QueryWrapper<SubmitStatusEntity>()
                                                .orderByDesc("submitTime"));
        List<SubmitStatusEntity> sList = page.getRecords();
        List<StatusVo> sl = new ArrayList<>();
        for (SubmitStatusEntity sse : sList) {
            sl.add(new StatusVo(sse.getId(),
                                sse.getPid(),
                                userMapper.selectNameById(sse.getAuthor()),
                                sse.getStatus(),
                                sse.getContent().length(),
                                sse.getSubmitTime()));
        }
        return sl;
    }

    public SolutionVo getSolution(Integer id) {
        SubmitStatusEntity se = statusMapper.selectById(id);
        return new SolutionVo(problemMapper.selectById(se.getPid()).getTitle(),
                                se.getSubmitTime(),
                                StatusUtil.getRealStatus(se.getStatus()),
                                se.getContent());
    }

    public Boolean checkPermission(String permission) {
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        if (user == null) return false;
        List<String> lp = redisService.getPermission(user);
        if (lp == null) return false;
        return lp.contains(permission);
    }

    public Boolean checkRole(String role) {
        UserRoleEntity ur = new UserRoleEntity();
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        ur.setUid(user.getUserId());
        QueryWrapper<UserRoleEntity> wrapper = new QueryWrapper<>(ur);
        List<UserRoleEntity> lru = userRoleMapper.selectList(wrapper);
        for (UserRoleEntity ue : lru) {
            if (roleService.getById(ue.getRid()).getRoleName().equals(role))
                return true;
        }
        return false;
    }

    public Boolean checkUser(Integer sid) {
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        SubmitStatusEntity se = statusMapper.selectById(sid);
        return user.getUserId().equals(se.getAuthor());
    }
}
