package cn.mir.background.management.aspect.cud.domain;

import lombok.Data;

/**
 * <p>Create time: 2019/4/13 3:18 PM</p>
 *
 * @author 周光兵
 */
@Data
public class ChangeItem {
    private String field;
    private String fieldName;
    private String oldValue;
    private String newValue;
}