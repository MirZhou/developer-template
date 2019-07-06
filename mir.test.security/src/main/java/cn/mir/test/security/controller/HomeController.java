package cn.mir.test.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Create time: 2019-05-02 19:57</p>
 *
 * @author 周光兵
 */
@Controller
public class HomeController {

    @GetMapping(value = "/user/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> test(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("id", id);
        map.put("username", "mir");
        map.put("realName", "zhou");

        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/users")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> users() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("username", "users");
        map.put("realName", "users");

        return ResponseEntity.ok(map);
    }
}
