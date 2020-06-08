package com.soullan.fakeojbe.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soullan.fakeojbe.modle.entity.UserEntity;
import com.soullan.fakeojbe.modle.vo.RankPageInfoVo;
import com.soullan.fakeojbe.modle.vo.RankVo;
import com.soullan.fakeojbe.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankService extends BaseService {
    private final UserService userService;
    private final UserUtil userUtil;

    @Autowired
    public RankService(UserService userService, UserUtil userUtil) {
        this.userService = userService;
        this.userUtil = userUtil;
    }

    public RankPageInfoVo getInfo() {
        return new RankPageInfoVo(userService.count());
    }

    public List<RankVo> getPage(Integer index, Integer size) {
        IPage<UserEntity> userEntityIPage = new Page<>(index, size);
        userEntityIPage = userService.page(userEntityIPage, new QueryWrapper<UserEntity>()
                                                                .orderByDesc("user_solved_count")
                                                                .orderByAsc("user_submit_count")
                                                                .orderByDesc("user_problem_count")
                                                                .orderByAsc("user_name"));
        List<UserEntity> uList = userEntityIPage.getRecords();
        List<RankVo> rList = new ArrayList<>();
        for (UserEntity ue : uList) {
            rList.add(new RankVo(ue.getUserId(), ue.getUserName(),
                                ue.getUserSolvedCount(),
                                ue.getUserSubmitCount(),
                                ue.getUserProblemCount(),
                                ue.getUserIsLocked(),
                                userUtil.canAdd(ue)));
        }
        return rList;
    }
}
