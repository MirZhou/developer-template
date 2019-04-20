package cn.mir.seckill.dto;

import lombok.Data;

/**
 * 封装返回结果
 * <p>Create time: 2019/4/20 5:47 PM</p>
 *
 * @author 周光兵
 */
@Data
public class SeckillResult<T> {
    private boolean success;
    private T data;
    private String message;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public SeckillResult(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }
}