package cn.mir.background.management.error;

import cn.mir.background.management.utils.validation.FieldErrorMessage;
import cn.mir.common.utilities.ResponseResult;
import cn.mir.common.utilities.error.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 控制器异常处理
 * <p>Create time: 2018/11/19 15:06</p>
 *
 * @author 周光兵
 */
@Slf4j
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseResult<Object>> handleMethodArgumentValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {
        // 错误信息列表
        List<FieldErrorMessage> errorMessages = this.getFieldErrorMessages(ex.getBindingResult());

        // 打印所有错误信息
        errorMessages.forEach(error -> log.error("{}", error));

        return this.getResponseEntity(request, HttpStatus.BAD_REQUEST, errorMessages.get(0).getErrorMessage(), ex);
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


    /**
     * 从错误绑定结果中，获取所有错误字段及其错误信息
     *
     * @param bindingResult 错误绑定结果
     * @return 错误信息列表
     */
    private List<FieldErrorMessage> getFieldErrorMessages(BindingResult bindingResult) {
        // 存放错误信息列表
        List<FieldErrorMessage> errorMessages = new ArrayList<>();

        // 获取要验证类的所有字段
        Field[] arrayField = Objects.requireNonNull(bindingResult.getTarget())
            .getClass()
            .getDeclaredFields();

        // 遍历字段
        for (Field field : arrayField) {
            // 获取字段名
            String fieldName = field.getName();

            // 根据字段名获取错误信息
            FieldError fieldError = bindingResult.getFieldError(fieldName);

            if (fieldError == null) {
                continue;
            }

            // 添加到错误信息列表中
            errorMessages.add(new FieldErrorMessage(fieldName, fieldError.getDefaultMessage()));
        }

        return errorMessages;
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