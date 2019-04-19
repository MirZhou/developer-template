package cn.mir.background.management.repository;

import cn.mir.background.management.entity.RoleModuleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao：角色模块菜单
 * <p>Create time: 2019/4/10 9:23 AM</p>
 *
 * @author 周光兵
 */
@Repository
public interface RoleModuleMenuDao extends JpaRepository<RoleModuleMenu, Integer> {
}