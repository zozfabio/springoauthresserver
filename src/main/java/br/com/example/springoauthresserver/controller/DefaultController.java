package br.com.example.springoauthresserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DefaultController {

    @RequestMapping("/get")
    public String get() {
        return "GET";
    }

    @RequestMapping("/list")
    public String list() {
        return "LIST";
    }
}
