package cn.mir.test.token.security.contorller;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Create time: 2020/4/17 00:45</p>
 *
 * @author 周光兵
 */
@Data
public class ValidationParameter implements Serializable {
    private static final long serialVersionUID = 7727781001555369102L;

    private String deviceId;
    private String deviceName;
    private Integer deviceType;
    private Long timestamp;
}
