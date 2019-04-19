package cn.mir.background.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 实体：工作流定义
 * <p>Create time: 2019/4/3 3:31 PM</p>
 *
 * @author 周光兵
 */
@Entity
@Table(name = "base_workflow_definition")
@EqualsAndHashCode(callSuper = true)
@Data
public class WorkflowDefinition extends BaseEntity {
    /**
     * 工作流ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL COMMENT '工作流ID'")
    private Integer id;
    /**
     * 所属公司ID
     */
    @Column(columnDefinition = "INT COMMENT '所属公司ID'")
    private Integer companyId;
    /**
     * 所属公司名称
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '所属公司名称'")
    private String companyName;
    /**
     * 所属部门ID
     */
    @Column(columnDefinition = "INT COMMENT '所属部门ID'")
    private Integer departmentId;
    /**
     * 所属部门名称
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '所属部门名称'")
    private String departmentName;
    /**
     * 工作流编号
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '工作流编号'")
    private String code;
    /**
     * 工作流名称
     */
    @Column(columnDefinition = "VARCHAR(200) COMMENT '工作流名称'")
    private String name;
    /**
     * 工作流内容
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '工作流内容'")
    private String content;
    /**
     * 排序值
     */
    @Column(columnDefinition = "INT COMMENT '排序值'")
    private Integer sortValue;
    /**
     * 标识是否启用。1：启用；0；禁用
     */
    @Column(columnDefinition = "INT NOT NULL DEFAULT 1 COMMENT '标识是否启用。1：启用；0：禁用'")
    private Integer markEnable;
    /**
     * 备注
     */
    @Column(columnDefinition = "VARCHAR(200) COMMENT '备注'")
    private String description;
}