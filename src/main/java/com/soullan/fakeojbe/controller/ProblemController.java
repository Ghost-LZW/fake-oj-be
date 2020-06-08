package com.soullan.fakeojbe.controller;

import com.soullan.fakeojbe.modle.entity.ProblemEntity;
import com.soullan.fakeojbe.modle.response.Result;
import com.soullan.fakeojbe.modle.vo.ProblemPageInfoVo;
import com.soullan.fakeojbe.modle.vo.ProblemVo;
import com.soullan.fakeojbe.service.ProblemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
public class ProblemController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(ProblemController.class);

    private final ProblemService problemService;

    @Autowired
    ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping("/getProblem")
    ProblemEntity getProblem(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        ProblemEntity problem =  problemService.getProblem(id);
        problem.setContent(problem.getContent().replace("\n", "<br>"));
        return problem;
    }

    @GetMapping("/problemList")
    List<ProblemVo> getProblemList(HttpServletRequest request) {

        Integer index = Integer.parseInt(request.getParameter("index"));
        Integer size = Integer.parseInt(request.getParameter("size"));
        return problemService.getPage(index, size);
    }

    @GetMapping("/problemPageInfo")
    ProblemPageInfoVo getProblemPageInfo() {
        return problemService.getInfo();
    }

    @PostMapping("/submitAnswer")
    @RequiresPermissions("PROBLEM:SUBMIT")
    Result CheckAnswer(HttpServletRequest request, @RequestBody Map<String, Object> params) {
        Integer pid = (Integer) params.get("pid");
        String answer = (String) params.get("answer");
        return problemService.getResult(pid, answer);
    }

    @PostMapping("/updateProblem")
    @RequiresPermissions("PROBLEM:ADD")
    Result UpdateProblem(HttpServletRequest request, @RequestBody Map<String, Object> params) {
        String id = params.get("id").toString();
        ProblemEntity problem = new ProblemEntity();
        problem.setTitle((String) params.get("title"));
        problem.setContent((String) params.get("content"));
        problem.setHint((String) params.get("hint"));
        String pData = (String) params.get("data");
        return problemService.updatePro(id, problem, pData);
    }

    @GetMapping("/getIndexProblem")
    List<ProblemVo> getIndexProblem(HttpServletRequest request) {
        Integer size = Integer.parseInt(request.getParameter("size"));
        return problemService.getRand(size);
    }
}
