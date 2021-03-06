package cn.mir.test.token.security.contorller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 控制类--首页
 * <p>
 * Create time: 2020/4/16 22:27
 * </p>
 *
 * @author 周光兵
 */
@Slf4j
@Controller
public class HomeController {
    @GetMapping(value = { "/", "/index" })
    public String index() {
        log.info("访问系统首页");

        return "index";
    }
}
