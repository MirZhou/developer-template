package cn.mir.background.management.controller;

import cn.mir.common.utilities.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 错误请求处理
 * 
 * @author 周光兵
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorHandlerController extends AbstractErrorController {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 错误页地址
     */
    private static final String ERROR_PATH = "/error";

    public ErrorHandlerController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request, Model model) {
        ResponseResult<Void> responseResult = this.getResponseResult(request);

        ModelAndView mv = new ModelAndView();
        mv.addObject("errorCode", responseResult.getCode());
        mv.addObject("errorMessage", responseResult.getMessage());
        mv.addObject("url", responseResult.getUrl());

        mv.setViewName("error");

        return mv;
    }

    /**
     * 返回类型依然为text/html，待解决
     */
    @RequestMapping
    @ResponseBody
    public ResponseEntity<ResponseResult<Void>> error(HttpServletRequest request) {
        ResponseResult<Void> result = this.getResponseResult(request);

        return ResponseEntity.status(HttpStatus.OK.value()).body(result);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * 获取错误信息
     */
    private ResponseResult<Void> getResponseResult(HttpServletRequest request) {
        int errorCode = (int) request.getAttribute("javax.servlet.error.status_code");
        String url = (String) request.getAttribute("javax.servlet.error.request_uri");
        String errorMessage = null;

        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        if (throwable != null) {
            errorMessage = throwable.getMessage();
        }

        if (errorMessage == null) {
            errorMessage = (String) request.getAttribute("javax.servlet.error.message");
        }

        ResponseResult<Void> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setCode(Integer.toString(errorCode));
        result.setMessage(errorMessage);
        result.setUrl(url);

        // 记录错误日志
        this.logger.error("请求执行错误。 ResponseResult:" + result, throwable);

        return result;
    }

}