package com.soullan.fakeojbe.controller;

import com.soullan.fakeojbe.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RestController
public class AdminController extends BaseController {
    private final AdminService adminService;

    @Autowired
    AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/lockOrUnlock")
    @RequiresPermissions("Admin")
    public String lockOrUnlock(HttpServletRequest request, @RequestBody Map<String, String> params) {
        Integer id = Integer.parseInt(params.get("id"));
        boolean locked = Boolean.parseBoolean(params.get("opt"));
        if (locked) {
            return adminService.unlock(id);
        } else {
            return adminService.lock(id);
        }
    }

    @PostMapping("/addOrCanNot")
    @RequiresPermissions("Admin")
    public String addOrNot(HttpServletRequest request, @RequestBody Map<String, String> params) {
        Integer id = Integer.parseInt(params.get("id"));
        boolean canAdd = Boolean.parseBoolean(params.get("opt"));
        if (canAdd) {
            return adminService.banAdd(id);
        } else {
            return adminService.liveAdd(id);
        }
    }
}
