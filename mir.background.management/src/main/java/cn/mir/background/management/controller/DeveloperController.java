package cn.mir.background.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * DeveloperController
 * 
 * @author Eros
 */
@Controller
@RequestMapping(value = "/developer")
public class DeveloperController {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 视图：关闭服务
     */
    @GetMapping(value = "/service/stop")
    public String close(){
        this.logger.warn("访问页面--》关闭服务");

        return "developer/close";
    }
}