package cn.eros.error;

import cn.mir.common.utilities.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Create time: 2020/8/6 20:52</p>
 *
 * @author 周光兵
 */
@Slf4j
@RestControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseResult<?>> handleAuthenticationException(HttpServletRequest request, AuthenticationException ex) {
        log.error("未登录用户访问 url: {} \t method: {} \t error: {}", request.getRequestURI(), request.getMethod(), ex.getMessage());

        ResponseResult<?> result = new ResponseResult<>(false, "非法访问");

        return ResponseEntity.ok(result);
    }
}
