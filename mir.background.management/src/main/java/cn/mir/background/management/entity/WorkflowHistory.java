package cn.mir.background.management.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 实体：工作流审批历史
 * <p>Create time: 2019/4/3 11:56 PM</p>
 *
 * @author 周光兵
 */
@Entity
@Table(name = "base_workflow_history")
@Data
public class WorkflowHistory {
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
    @Column(columnDefinition = "INT COMMENT '所属工作流ID'")
    private Integer workflowId;
    /**
     * 所属工作流步骤ID
     */
    @Column(columnDefinition = "INT COMMENT '所属工作流步骤ID'")
    private Integer workflowStepId;
}