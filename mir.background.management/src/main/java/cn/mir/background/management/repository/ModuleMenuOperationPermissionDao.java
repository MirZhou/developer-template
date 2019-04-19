package cn.mir.background.management.repository;

import cn.mir.background.management.entity.ModuleMenuOperationPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao：模块菜单操作权限
 * <p>Create time: 2019/4/10 9:12 AM</p>
 *
 * @author 周光兵
 */
@Repository
public interface ModuleMenuOperationPermissionDao extends JpaRepository<ModuleMenuOperationPermission, Integer> {
}