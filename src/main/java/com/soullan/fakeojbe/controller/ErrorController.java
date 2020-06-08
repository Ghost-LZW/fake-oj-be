package com.soullan.fakeojbe.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController extends BaseController implements org.springframework.boot.web.servlet.error.ErrorController {
    /**
     * @deprecated
     */
    @Override
    @RequestMapping("/error")
    @ResponseBody
    public String getErrorPath() {
        return "No Mapping found";
    }
}
