package com.soullan.fakeojbe.dao.session;

import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.stereotype.Component;

@Component
public class RedisSession extends EnterpriseCacheSessionDAO {

}
