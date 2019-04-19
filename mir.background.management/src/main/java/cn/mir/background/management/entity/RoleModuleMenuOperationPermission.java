package cn.mir.background.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 实体：角色模块菜单操作权限
 * <p>Create time: 2019/4/1 1:21 PM</p>
 *
 * @author 周光兵
 */
@Entity
@Table(name = "base_role_module_menu_operation_permission")
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleModuleMenuOperationPermission extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT COMMENT '主键ID'")
    private Integer id;
    /**
     * 角色ID
     */
    @Column(columnDefinition = "INT COMMENT '角色ID'")
    private Integer roleId;
    /**
     * 模块菜单ID
     */
    @Column(columnDefinition = "INT COMMENT '模块菜单ID'")
    private Integer moduleId;
    /**
     * 模块菜单操作权限ID
     */
    @Column(columnDefinition = "INT COMMENT '模块菜单操作权限ID'")
    private Integer modulePermissionId;
}