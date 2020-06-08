package com.soullan.fakeojbe.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soullan.fakeojbe.dao.mapper.ProblemDataMapper;
import com.soullan.fakeojbe.dao.mapper.ProblemMapper;
import com.soullan.fakeojbe.modle.entity.ProblemDataEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JudgeService {

    @Resource
    ProblemMapper problemMapper;

    @Resource
    ProblemDataMapper problemDataMapper;

    public Integer judge(Integer pid, String answer) {
        ProblemDataEntity data = new ProblemDataEntity();
        data.setPid(pid);
        QueryWrapper<ProblemDataEntity> wrapper = new QueryWrapper<>(data);
        List<ProblemDataEntity> datas = problemDataMapper.selectList(wrapper);
        int result = 0;
        for (ProblemDataEntity i : datas)
            if (i.getData().equals(answer)) {
                result = 1;
                break;
            }
        return result;
    }
}
