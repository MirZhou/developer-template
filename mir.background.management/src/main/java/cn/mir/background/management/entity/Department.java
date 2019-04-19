package cn.mir.background.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 实体：组织结构（部门）
 * <p>Create time: 2019/3/24 11:00 PM</p>
 *
 * @author 周光兵
 */
@Entity
@Table(name = "base_department")
@EqualsAndHashCode(callSuper = true)
@Data
public class Department extends BaseEntity {
    /**
     * 组织/部门ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL COMMENT '组织/部门ID'")
    private Integer id;
    /**
     * 上级组织/部门ID
     */
    @Column(columnDefinition = "INT DEFAULT 0 COMMENT '上级组织/部门ID'")
    private Integer parentId;
    /**
     * 编号
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '部门编号'")
    private String code;
    /**
     * 名称
     */
    @Column(columnDefinition = "VARCHAR(100) NOT NULL COMMENT '部门名称'")
    private String name;
    /**
     * 内部组织标识。1：内部组织；0：外部组织
     */
    @Column(columnDefinition = "INT DEFAULT 1 COMMENT '内部组织标识。1：内部组织；0：外部组织'")
    private Integer markInternal;
    /**
     * 排序值
     */
    @Column(columnDefinition = "INT COMMENT '排序值'")
    private Integer sortValue;
    /**
     * 描述
     */
    @Column(columnDefinition = "VARCHAR(200) COMMENT '描述'")
    private String description;
}