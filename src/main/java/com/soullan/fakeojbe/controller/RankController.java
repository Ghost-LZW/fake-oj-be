package com.soullan.fakeojbe.controller;

import com.soullan.fakeojbe.modle.vo.RankPageInfoVo;
import com.soullan.fakeojbe.modle.vo.RankVo;
import com.soullan.fakeojbe.service.RankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class RankController extends BaseController {

    private final RankService rankService;

    @Autowired
    RankController (RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping("/rankInfo")
    RankPageInfoVo getRankInfo(HttpServletRequest request) {
        return rankService.getInfo();
    }

    @GetMapping("/rankList")
    List<RankVo> getRankPage(HttpServletRequest request) {
        Integer index = Integer.parseInt(request.getParameter("index"));
        Integer size = Integer.parseInt(request.getParameter("size"));
        return rankService.getPage(index, size);
    }
}
