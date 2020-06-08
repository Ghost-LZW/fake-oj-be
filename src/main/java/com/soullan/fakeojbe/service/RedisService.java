package com.soullan.fakeojbe.service;

import com.soullan.fakeojbe.modle.entity.UserEntity;
import com.soullan.fakeojbe.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService extends BaseService {

    public final RedisTemplate redisTemplate;

    public final ValueOperations ops;

    private final UserService userService;

    @Autowired
    public RedisService(RedisTemplate redisTemplate, UserService userService) {
        this.redisTemplate = redisTemplate;
        ops = redisTemplate.opsForValue();

        this.userService = userService;
    }

    public void setVal(String key, String val) {
        if (RedisUtil.isEmpty(key) || RedisUtil.isEmpty(val)) return;
        ops.set(key, val);
    }

    public void setVal(UserEntity user, List<String> per) {
        ops.set(user, per);
    }

    public List<String> getPermission(UserEntity user) {
        List<String> res = (List<String>) ops.get(user);
        if (res == null) setVal(user, res = userService.getPermission(user));
        return res;
    }
}
