package cn.mir.background.management.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HomeController
 * <p>
 * Create time: 2019/8/18 23:50
 * </p>
 * 
 * @author Eros
 */
@Controller
public class HomeController {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 首页
     */
    @GetMapping(value = "/index")
    public String index(HttpServletRequest request, int value) {
        this.logger.info("访问首页 QueryString --> {}", request.getQueryString());
        
        this.logger.info("value:{}, 10/value : {}", value, (10 / value));

        return "index";
    }

    @GetMapping(value = "/ajaxTest/{value}")
    @ResponseBody
    public int ajaxTest(@PathVariable Integer value) {
        this.logger.info("value:{}, 10/value : {}", value, (10 / value));

        return 10 / value;
    }
}