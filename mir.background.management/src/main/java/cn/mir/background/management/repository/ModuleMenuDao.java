package cn.mir.background.management.repository;

import cn.mir.background.management.entity.ModuleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao：模块菜单
 * <p>Create time: 2019/4/10 9:10 AM</p>
 *
 * @author 周光兵
 */
@Repository
public interface ModuleMenuDao extends JpaRepository<ModuleMenu, Integer> {
}