package cn.mir.background.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 实体：模块菜单
 * <p>Create time: 2019/3/30 12:04 PM</p>
 *
 * @author 周光兵
 */
@Entity
@Table(name = "base_module_menu")
@EqualsAndHashCode(callSuper = true)
@Data
public class ModuleMenu extends BaseEntity {
    /**
     * 模块ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL COMMENT '模块ID'")
    private Integer id;
    /**
     * 上级模块ID。0：根模块
     */
    @Column(columnDefinition = "INT DEFAULT 0 COMMENT '上级模块ID。0：根模块'")
    private Integer parentId;
    /**
     * 模块编号
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '模块编号'")
    private String code;
    /**
     * 模块名称
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '模块名称'")
    private String name;
    /**
     * 模块访问地址
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '模块访问地址'")
    private String url;
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