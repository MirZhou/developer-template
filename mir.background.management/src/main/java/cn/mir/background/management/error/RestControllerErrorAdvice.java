package cn.mir.background.management.error;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * RestControllerErrorAdvice
 * 
 * @author 周光兵
 */
@RestControllerAdvice
public class RestControllerErrorAdvice {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> exceptionHandler(HttpServletRequest request, Exception ex) {
        this.logger.error("请求执行异常 url:{} message:{} --->{}", request.getRequestURI(), ex.getMessage(), ex.getCause());

        Map<String, Object> map = new HashMap<>(2);
        map.put("success", false);
        map.put("message", ex.getMessage());

        return map;
    }
}