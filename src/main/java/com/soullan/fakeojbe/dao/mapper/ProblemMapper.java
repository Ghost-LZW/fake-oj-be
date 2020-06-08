package com.soullan.fakeojbe.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soullan.fakeojbe.modle.entity.ProblemEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProblemMapper extends BaseMapper<ProblemEntity> {
    @Select("select id FROM fake_problems WHERE title=#{proName}")
    public Integer selectIdByTitle(@Param("proName") String proName);

    @Select("select * from fake_problems order by rand() limit #{Num}")
    public List<ProblemEntity> selectByRand(@Param("Num") Integer Num);
}
