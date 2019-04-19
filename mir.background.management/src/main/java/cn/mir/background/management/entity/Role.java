package cn.mir.background.management.entity;

import cn.mir.background.management.aspect.cud.DataLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 实体：角色
 * <p>Create time: 2019/3/19 2:30 AM</p>
 *
 * @author 周光兵
 */
@Entity
@Table(name = "base_role")
@EqualsAndHashCode(callSuper = true)
@Data
public class Role extends BaseEntity {
    /**
     * 角色ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL COMMENT '角色ID'")
    @DataLog(name = "角色ID")
    private Integer id;
    /**
     * 编号
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '编号'")
    @DataLog(name = "编号")
    private String code;
    /**
     * 名称
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '名称'")
    @DataLog(name = "名称")
    private String name;
    /**
     * 角色分类。1：角色；2：用户组
     */
    @Column(columnDefinition = "INT NOT NULL DEFAULT 1 COMMENT '角色分类。1：角色；2：用户组'")
    @DataLog(name = "角色分类")
    private Integer roleType;
    /**
     * 启用状态。1：启用；0；禁用
     */
    @Column(columnDefinition = "INT NOT NULL DEFAULT 1 COMMENT '标识是否启用。1：启用；0：禁用'")
    @DataLog(name = "启用状态")
    private Integer markEnable;
    /**
     * 描述
     */
    @Column(columnDefinition = "VARCHAR(200) COMMENT '描述'")
    @DataLog(name = "描述")
    private String description;
}