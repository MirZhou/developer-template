package cn.mir.common.utilities;

import lombok.Data;

/**
 * 接口响应结果集
 * <p>
 * Create time: 2019/4/30 4:45 PM
 * </p>
 *
 * @author 周光兵
 */
@Data
public class ResponseResult<T> {
    /**
     * 标识接口是否成功执行
     */
    private Boolean success;
    /**
     * Http状态码
     */
    private Integer code;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 接口返回消息
     */
    private String message;
    /**
     * 接口返回对象
     */
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public ResponseResult(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}