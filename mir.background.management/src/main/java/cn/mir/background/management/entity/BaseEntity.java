package cn.mir.background.management.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 实体通用基类
 * <p>Create time: 2019/3/24 10:54 PM</p>
 *
 * @author 周光兵
 */
@MappedSuperclass
@Data
public class BaseEntity {
    /**
     * 删除标识。1：已删除；0：未删除
     */
    @Column(columnDefinition = "INT NOT NULL DEFAULT 0 COMMENT '删除标识。1：已删除；0：未删除'")
    private Integer markDelete;
    /**
     * 创建时间
     */
    @Column(columnDefinition = "DATETIME(6) COMMENT '创建时间'")
    private LocalDateTime createTime;
    /**
     * 创建用户ID
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '创建用户ID'")
    private String createUserId;
    /**
     * 创建用户真实姓名
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '创建用户真实姓名'")
    private String createUserRealName;
    /**
     * 最后修改时间
     */
    @Column(columnDefinition = "DATETIME(6) COMMENT '最后修改时间'")
    private LocalDateTime modifyTime;
    /**
     * 最后一次修改用户的ID
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '最后一次修改用户的ID'")
    private String modifyUserId;
    /**
     * 最后一次修改用户的真实姓名
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '最后一次修改用户的真实姓名'")
    private String modifyUserRealName;
}
