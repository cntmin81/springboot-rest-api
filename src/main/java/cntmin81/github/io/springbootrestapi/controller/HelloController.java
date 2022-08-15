package cntmin81.github.io.springbootrestapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cntmin81.github.io.springbootrestapi.model.Hello;

@Controller
public class HelloController {

    // 문자열을 반환
    @GetMapping("/hello/string")
    @ResponseBody // 문자열 또는 객체를 바로 반환한다.
    public String helloString() {
        return "hello";
    }

    // JSON을 반환
    @GetMapping("/hello/json")
    @ResponseBody
    public Hello helloJson() {
        Hello hello = new Hello();
        hello.setMessage("hello");
        return hello;
    }

    // 뷰를 반환
    @GetMapping("/hello/view")
    public String helloView() {
        return "hello";
    }
}
