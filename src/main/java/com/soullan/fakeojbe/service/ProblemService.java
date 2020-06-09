package com.soullan.fakeojbe.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soullan.fakeojbe.dao.mapper.ProblemDataMapper;
import com.soullan.fakeojbe.dao.mapper.ProblemMapper;
import com.soullan.fakeojbe.dao.mapper.StatusMapper;
import com.soullan.fakeojbe.dao.mapper.UserRoleMapper;
import com.soullan.fakeojbe.modle.entity.*;
import com.soullan.fakeojbe.modle.response.Result;
import com.soullan.fakeojbe.modle.vo.ProblemPageInfoVo;
import com.soullan.fakeojbe.modle.vo.ProblemVo;
import com.soullan.fakeojbe.util.ProblemUtil;
import com.soullan.fakeojbe.util.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemService extends BaseService {
    private final Logger logger = LoggerFactory.getLogger(ProblemService.class);

    private final ProblemMapper problemMapper;
    private final JudgeService judgeService;
    private final StatusMapper statusMapper;
    private final ProblemDataMapper problemDataMapper;
    private final RoleService roleService;
    private final UserRoleMapper userRoleMapper;
    private final RedisService redisService;
    private final UserService userService;

    @Autowired
    ProblemService(ProblemMapper problemMapper, JudgeService judgeService, StatusMapper statusMapper,
                   ProblemDataMapper problemDataMapper, RoleService roleService,
                   UserRoleMapper userRoleMapper, RedisService redisService,
                   UserService userService) {
        this.judgeService = judgeService;
        this.problemMapper = problemMapper;
        this.statusMapper = statusMapper;
        this.problemDataMapper = problemDataMapper;
        this.roleService = roleService;
        this.userRoleMapper = userRoleMapper;
        this.redisService = redisService;
        this.userService = userService;
    }

    public List<ProblemVo> getPage(Integer index, Integer size) {
        IPage<ProblemEntity> page = new Page<>(index, size);
        IPage<ProblemEntity> res = problemMapper.selectPage(page, null);
        List<ProblemEntity> listP = res.getRecords();
        List<ProblemVo> list = new ArrayList<>();
        for (ProblemEntity i : listP) {
            list.add(new ProblemVo(i.getId(), i.getSubmitCount(), i.getSolvedCount(), i.getTitle()));
        }
        return list;
    }

    public List<ProblemVo> getRand(Integer size) {
        List<ProblemEntity> plist = problemMapper.selectByRand(size);
        List<ProblemVo> list = new ArrayList<>();
        for (ProblemEntity i : plist) {
            list.add(new ProblemVo(i.getId(), i.getSubmitCount(), i.getSolvedCount(), i.getTitle()));
        }
        return list;
    }

    public ProblemEntity getProblem(Integer id) {
        return problemMapper.selectById(id);
    }

    public ProblemPageInfoVo getInfo() {
        return new ProblemPageInfoVo(problemMapper.selectCount(null));
    }

    public Result getResult(Integer pid, String answer) {
        try {
            Integer status = judgeService.judge(pid, answer);
            UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
            SubmitStatusEntity statueEntity = new SubmitStatusEntity();
            statueEntity.setPid(pid);
            statueEntity.setStatus(status);
            statueEntity.setAuthor(user.getUserId());
            statueEntity.setContent(answer);

            ProblemEntity problem = problemMapper.selectById(pid);

            SubmitStatusEntity se = new SubmitStatusEntity();
            se.setPid(pid);
            se.setAuthor(user.getUserId());
            se.setStatus(status);
            QueryWrapper<SubmitStatusEntity> wrapper = new QueryWrapper<>(se).last("LIMIT 1");

            boolean never = true;
            if (statusMapper.selectOne(wrapper) != null) never = false;

            problem.setSubmitCount(problem.getSubmitCount() + 1);
            user.setUserSubmitCount(user.getUserSubmitCount() + 1);
            if (status == 1) {
                if (never) user.setUserSolvedCount(user.getUserSolvedCount() + 1);
                problem.setSolvedCount(problem.getSolvedCount() + 1);
            }
            problemMapper.updateById(problem);
            statusMapper.insert(statueEntity);
            userService.updateById(user);

            return ResultUtil.success();
        } catch (Exception e) {
            logger.error(e.toString());
            return ResultUtil.fail();
        }
    }

    public List<ProblemDataEntity> parseData(Integer pid, String pData) {
        List<ProblemDataEntity> plist = new ArrayList<>();
        for (String data : pData.split(";")) {
            ProblemDataEntity tp = new ProblemDataEntity();
            tp.setPid(pid); tp.setData(data);
            plist.add(tp);
        }
        return plist;
    }

    public Result updatePro(String id, ProblemEntity problem, String pData) {
        try {
            Integer pid;
            if (ProblemUtil.isEmpty(id) || problemMapper.selectById(Integer.parseInt(id)) == null) {
                problemMapper.insert(problem);
                pid = problemMapper.selectIdByTitle(problem.getTitle());
                for (ProblemDataEntity pd : parseData(pid, pData))
                    problemDataMapper.insert(pd);
                UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
                user.setUserProblemCount(user.getUserProblemCount() + 1);
                userService.updateById(user);
            } else {
                pid = Integer.parseInt(id);
                problem.setId(pid);
                problemMapper.updateById(problem);
                if (!ProblemUtil.isEmpty(pData)) {
                    ProblemDataEntity temp = new ProblemDataEntity();
                    temp.setPid(pid);
                    QueryWrapper<ProblemDataEntity> wrapper = new QueryWrapper<>(temp);
                    problemDataMapper.delete(wrapper);
                    for (ProblemDataEntity pd : parseData(pid, pData))
                        problemDataMapper.insert(pd);
                }
            }
            return  ResultUtil.dSuccess(pid);
        } catch (Exception e) {
            logger.error(e.toString());
            return ResultUtil.fail();
        }
    }
}
