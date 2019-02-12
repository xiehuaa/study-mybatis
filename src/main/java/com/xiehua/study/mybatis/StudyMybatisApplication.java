package com.xiehua.study.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is Description
 *
 * @author xiehua
 * @date 2019/02/12
 */
@SpringBootApplication
@RestController
public class StudyMybatisApplication {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "HELLO WORLD !!!";
    }
    public static void main(String[] args) {
        SpringApplication.run(StudyMybatisApplication.class, args);
    }
}
