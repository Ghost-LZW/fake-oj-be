package com.soullan.fakeojbe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soullan.fakeojbe.dao.mapper.NewsMapper;
import com.soullan.fakeojbe.modle.entity.NewsEntity;
import com.soullan.fakeojbe.service.NewsService;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, NewsEntity> implements NewsService {

}
