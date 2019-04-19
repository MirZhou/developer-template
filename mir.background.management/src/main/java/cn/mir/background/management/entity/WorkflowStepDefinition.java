package cn.mir.background.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 实体：工作流步骤定义
 * <p>Create time: 2019/4/3 5:48 PM</p>
 *
 * @author 周光兵
 */
@Entity
@Table(name = "base_workflow_step_definition")
@EqualsAndHashCode(callSuper = true)
@Data
public class WorkflowStepDefinition extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL COMMENT '主键ID'")
    private Integer id;
    /**
     * 所属工作流ID
     */
    @Column(columnDefinition = "INT NOT NULL COMMENT '所属工作流ID'")
    private Integer workflowId;
    /**
     * 流程名称
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '流程名称'")
    private String name;
    /**
     * 审核部门ID
     */
    @Column(columnDefinition = "INT COMMENT '审核部门ID'")
    private Integer auditDepartmentId;
    /**
     * 审核部门名称
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '审核部门名称'")
    private String auditDepartmentName;
    /**
     * 审核员ID
     */
    @Column(columnDefinition = "INT COMMENT '审核员ID'")
    private Integer auditorId;
    /**
     * 审核员名称
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '审核员名称'")
    private String auditorName;
    /**
     * 审核角色ID
     */
    @Column(columnDefinition = "INT COMMENT '审核角色ID'")
    private Integer auditRoleId;
    /**
     * 审核角色名称
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '审核角色名称'")
    private String auditRoleName;
    /**
     * 审核类别
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '审核类别'")
    private String auditCategory;
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