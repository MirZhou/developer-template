package cn.mir.test.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页
 * <p>Create time: 2020/3/20 15:55</p>
 *
 * @author 周光兵
 */
@Controller
public class IndexController {
    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
}
