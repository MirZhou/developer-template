package cn.mir.background.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * AccountController
 * 
 * @author Eros
 */
@Controller
public class AccountController {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 视图：登录
     */
    @GetMapping(value = "/login")
    public String login() {
        this.logger.info("访问登录页");

        return "login";
    }
}