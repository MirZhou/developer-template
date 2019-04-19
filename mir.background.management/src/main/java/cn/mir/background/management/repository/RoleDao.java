package cn.mir.background.management.repository;

import cn.mir.background.management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao：角色
 * <p>Create time: 2019/4/10 9:22 AM</p>
 *
 * @author 周光兵
 */
@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
}