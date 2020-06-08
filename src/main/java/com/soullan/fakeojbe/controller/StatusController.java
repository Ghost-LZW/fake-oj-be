package com.soullan.fakeojbe.controller;

import com.soullan.fakeojbe.modle.vo.SolutionVo;
import com.soullan.fakeojbe.modle.vo.StatusPageInfoVo;
import com.soullan.fakeojbe.modle.vo.StatusVo;
import com.soullan.fakeojbe.service.StatusService;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class StatusController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(StatusController.class);

    StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/getStatusInfo")
    StatusPageInfoVo getStatusInfo(HttpServletRequest request) {
        return statusService.getInfo();
    }

    @GetMapping("/statusList")
    List<StatusVo> getStatusList(HttpServletRequest request) {
        Integer index = Integer.parseInt(request.getParameter("index"));
        Integer size = Integer.parseInt(request.getParameter("size"));
        return statusService.getPage(index, size);
    }

    @PostMapping("/getSolution")
    SolutionVo getSolution(HttpServletRequest request, @RequestBody Map<String, String> params) {
        Boolean checkP = statusService.checkPermission("Admin");
        Integer id = Integer.parseInt(params.get("id"));

        Boolean checkR = statusService.checkUser(id);
        if (!checkP && !checkR) {
            throw new AuthenticationException();
        }

        return statusService.getSolution(id);
    }
}
