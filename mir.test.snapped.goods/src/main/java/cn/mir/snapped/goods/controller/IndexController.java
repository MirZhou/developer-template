package cn.mir.snapped.goods.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mir.common.utilities.ResponseResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 首页入口
 * <p>
 * Create time:2020-03-26 22:54:37
 * </p>
 * 
 * @author ErosZhou
 */
@Slf4j
@Controller
public class IndexController {
    @GetMapping(value = {"/","/purchase"})
    public String test() {
        log.info("测试访问");

        return "purchase";
    }
}