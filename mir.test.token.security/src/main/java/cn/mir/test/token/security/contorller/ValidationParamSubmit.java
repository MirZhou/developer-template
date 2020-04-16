package cn.mir.test.token.security.contorller;

import lombok.Data;

/**
 * <p>Create time: 2020/4/17 00:39</p>
 *
 * @author 周光兵
 */
@Data
public class ValidationParamSubmit {
    private String username;
    private String deviceId;
    private String deviceName;
    private Integer deviceType;

    public ValidationParameter translateToParameter() {
        ValidationParameter parameter = new ValidationParameter();
        parameter.setDeviceId(this.deviceId);
        parameter.setDeviceName(this.deviceName);
        parameter.setDeviceType(this.deviceType);

        return parameter;
    }
}
