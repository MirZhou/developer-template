package cn.mir.background.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * CloseServiceController
 * 
 * @author Eros·Zhou
 */
@Controller
public class CloseServiceController {
    @GetMapping(value = "/service/stop")
    public String close(){
        return "close";
    }
}