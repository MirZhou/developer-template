package cn.mir.background.management.repository;

import cn.mir.background.management.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao：组织结构（部门）
 * <p>Create time: 2019/4/10 9:08 AM</p>
 *
 * @author 周光兵
 */
@Repository
public interface DepartmentDao extends JpaRepository<Department, Integer> {
}