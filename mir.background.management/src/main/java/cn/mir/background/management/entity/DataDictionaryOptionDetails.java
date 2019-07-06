package cn.mir.background.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 实体：数据字典选项明细参考类
 * <p>Create time: 2019/3/29 9:21 PM</p>
 *
 * @author 周光兵
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataDictionaryOptionDetails extends BaseEntity {
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