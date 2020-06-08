package com.soullan.fakeojbe.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soullan.fakeojbe.modle.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<UserEntity> {
    @Select("select * from `fake_user` ORDER BY RAND() LIMIT 1")
    public UserEntity selectRandOne();

    @Select("select user_name from fake_user where user_id=#{Id}")
    public String selectNameById(@Param("Id") Integer Id);
}
