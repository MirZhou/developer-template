package cn.mir.background.management.dto.submit;

import cn.mir.background.management.utils.validation.AbstractParamValid;
import cn.mir.background.management.utils.validation.FieldErrorMessage;
import cn.mir.common.utilities.validator.groups.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * 提交数据接收类：用户信息提交
 * <p>Create time: 2020/4/25 15:57</p>
 *
 * @author 周光兵
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserSubmit extends AbstractParamValid {
    /**
     * ID
     */
    @NotNull(message = "id不能为空", groups = Update.class)
    private Long id;
    /**
     * 用户名
     */
    @NotBlank(message = "姓名不能为空")
    private String username;
    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空")
    private String gender;
    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phone;
    /**
     * 住址
     */
    private String address;
    /**
     * 备注
     */
    private String remark;

    @Override
    public List<FieldErrorMessage> checkParameters() {
        return Collections.singletonList(new FieldErrorMessage("address", "住址不能为空"));
    }
}
