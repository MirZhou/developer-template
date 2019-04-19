package cn.mir.background.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 实体：数据字典选项
 * <p>Create time: 2019/3/29 9:21 PM</p>
 *
 * @author 周光兵
 */
@Entity
@Table(name = "base_data_dictionary_option")
@EqualsAndHashCode(callSuper = true)
@Data
public class DataDictionaryOption extends BaseEntity {
    /**
     * 主键ID值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL COMMENT 'ID'")
    private Integer id;
    /**
     * 编号
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '编号'")
    private String code;
    /**
     * 名称
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '名称'")
    private String name;
    /**
     * 目标表
     */
    @Column(columnDefinition = "VARCHAR(100) NOT NULL COMMENT '目标表'")
    private String targetTable;
    /**
     * 备注
     */
    @Column(columnDefinition = "VARCHAR(200) COMMENT '备注'")
    private String description;
    /**
     * 标识是否启用编号列。1：启用；0；禁用
     */
    @Column(columnDefinition = "INT NOT NULL DEFAULT 1 COMMENT '标识是否启用编号列。1：启用；0：禁用'")
    private Integer markEnableCode;
    /**
     * 标识是否启用名称列。1：启用；0；禁用
     */
    @Column(columnDefinition = "INT NOT NULL DEFAULT 1 COMMENT '标识启用状态。1：启用；0：禁用'")
    private Integer markEnableName;
    /**
     * 标识是否启用记录值列。1：启用；0；禁用
     */
    @Column(columnDefinition = "INT NOT NULL DEFAULT 1 COMMENT '标识是否启用记录值列。1：启用；0：禁用'")
    private Integer markEnableValue;
    /**
     * 标识是否启用。1：启用；0；禁用
     */
    @Column(columnDefinition = "INT NOT NULL DEFAULT 1 COMMENT '标识是否启用。1：启用；0：禁用'")
    private Integer markEnable;
}