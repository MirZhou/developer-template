package cn.mir.background.management.error;

import cn.mir.common.utilities.ResponseResult;
import cn.mir.common.utilities.error.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器异常处理
 * <p>Create time: 2018/11/19 15:06</p>
 *
 * @author 周光兵
 */
@RestControllerAdvice
public class ExceptionMapper {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(ExceptionMapper.class);

    @ExceptionHandler(NotModifiedException.class)
    public ResponseEntity<ResponseResult<Object>> handleNotModified(HttpServletRequest request, NotModifiedException ex) {
        return this.getResponseEntity(request, HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseResult<Object>> handleNotFound(HttpServletRequest request, EntityNotFoundException ex) {
        return this.getResponseEntity(request, HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResponseResult<Object>> handleNotModified(HttpServletRequest request, UnauthorizedException ex) {
        return this.getResponseEntity(request, HttpStatus.UNAUTHORIZED, ex);
    }

    @ExceptionHandler(ParamErrorException.class)
    public ResponseEntity<ResponseResult<Object>> handleParamError(HttpServletRequest request, ParamErrorException ex) {
        return this.getResponseEntity(request, HttpStatus.BAD_REQUEST, ex);
    }

    /**
     * 用户认证失败
     */
    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<ResponseResult<Object>> handleIncorrectCredentials(HttpServletRequest request, IncorrectCredentialsException ex) {
        return this.getResponseEntity(request, HttpStatus.FORBIDDEN, "sessionTimeout", ex);
    }

    /**
     * 请求参数解析失败
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseResult<Object>> handlerHttpMessageNotReadable(HttpServletRequest request, HttpMessageNotReadableException ex) {
        return this.getResponseEntity(request, HttpStatus.FORBIDDEN, "参数解析异常", ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseResult<Object>> handleException(HttpServletRequest request, Exception ex) {
        return this.getResponseEntity(request, HttpStatus.BAD_REQUEST, ex);
    }

    private ResponseEntity<ResponseResult<Object>> getResponseEntity(HttpServletRequest request, HttpStatus httpStatus, Exception ex) {
        return this.getResponseEntity(request, httpStatus, null, ex);
    }

    /**
     * 返回异常响应实体
     *
     * @param httpStatus HTTP响应码
     * @param ex         异常对象
     * @return 返回异常响应实体
     */
    private ResponseEntity<ResponseResult<Object>> getResponseEntity(HttpServletRequest request, HttpStatus httpStatus, String errorMessage, Exception ex) {
        // 记录日志
        this.logger.error("服务器执行异常 message:{} status:{}, url:{}", ex.getMessage(), httpStatus.value(), request.getRequestURI(), ex);

        String message = StringUtils.isBlank(errorMessage) ? ex.getMessage() : errorMessage;

        ResponseResult<Object> responseResult = new ResponseResult<>(false, message);

        return this.generated(responseResult);
    }

    private ResponseEntity<ResponseResult<Object>> generated(ResponseResult<Object> responseResult) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(responseResult);
    }

} // end public class ExceptionMapper