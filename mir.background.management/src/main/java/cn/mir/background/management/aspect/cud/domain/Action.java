package cn.mir.background.management.aspect.cud.domain;

import cn.mir.background.management.aspect.cud.ActionType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Create time: 2019/4/13 3:56 PM</p>
 *
 * @author 周光兵
 */
@Data
public class Action {
    private String id;
    private String objectId;
    private String objectClass;
    private String operator;
    private LocalDateTime operateTime;
    private ActionType actionType;
    List<ChangeItem> changes = new ArrayList<>();
}
