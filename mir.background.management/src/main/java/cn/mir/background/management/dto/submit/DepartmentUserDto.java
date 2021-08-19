package cn.mir.background.management.dto.submit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>create time：2021-08-16 16:14
 *
 * @author Eros
 */
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class DepartmentUserDto {
    private String code;
    private String realName;
    private String departmentName;

    public DepartmentUserDto(String code, String realName, String departmentName) {
        this.code = code;
        this.realName = realName;
        this.departmentName = departmentName;
    }
}
