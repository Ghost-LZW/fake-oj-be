package com.soullan.fakeojbe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "Greeting from Spring boot";
    }
}
