package cn.mir.background.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 实体：模块菜单操作权限
 * <p>Create time: 2019/3/30 12:05 PM</p>
 *
 * @author 周光兵
 */
@Entity
@Table(name = "base_module_menu_operation_permission")
@EqualsAndHashCode(callSuper = true)
@Data
public class ModuleMenuOperationPermission extends BaseEntity {
    /**
     * 操作权限ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL COMMENT '模块ID'")
    private Integer id;
    /**
     * 权限所属模块ID
     */
    @Column(columnDefinition = "INT NOT NULL COMMENT '权限所属模块ID'")
    private Integer moduleId;
    /**
     * 操作权限编号
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '操作权限编号'")
    private String code;
    /**
     * 操作权限名称
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '操作权限名称'")
    private String name;
    /**
     * 标识是否启用数据集权限。1：启用；0；禁用
     */
    @Column(columnDefinition = "INT NOT NULL DEFAULT 1 COMMENT '标识是否启用数据集权限。1：启用；0：禁用'")
    private Integer markDataSet;
    /**
     * 标识是否启用。1：启用；0；禁用
     */
    @Column(columnDefinition = "INT NOT NULL DEFAULT 1 COMMENT '标识是否启用。1：启用；0：禁用'")
    private Integer markEnable;
    /**
     * 排序值
     */
    @Column(columnDefinition = "INT COMMENT '排序值'")
    private Integer sortValue;
    /**
     * 备注
     */
    @Column(columnDefinition = "VARCHAR(200) COMMENT '备注'")
    private String description;
}