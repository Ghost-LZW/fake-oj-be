package com.soullan.fakeojbe.controller;

import com.soullan.fakeojbe.dao.mapper.UserMapper;
import com.soullan.fakeojbe.modle.entity.NewsEntity;
import com.soullan.fakeojbe.modle.entity.UserEntity;
import com.soullan.fakeojbe.modle.vo.NewsVo;
import com.soullan.fakeojbe.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NewsController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(NewsController.class);

    private final NewsService newsService;

    @Resource
    private UserMapper userMapper;

    @Autowired
    NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public List<NewsVo> getNews() {

        List<NewsVo> list = new ArrayList<>();
        for (NewsEntity t : newsService.list()) {
            if (!t.getNeed()) list.add(new NewsVo(t.getTitle(), t.getContent()));
            else {
                UserEntity user = userMapper.selectRandOne();
                list.add(new NewsVo(t.getTitle(),
                        String.format(t.getContent(), user.getUserName(), user.getUserSolvedCount())));
            }
        }
        return list;
    }

}
