package cn.mir.background.management.repository;

import cn.mir.background.management.entity.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao：用户登录日志
 * <p>Create time: 2019/4/10 9:20 AM</p>
 *
 * @author 周光兵
 */
@Repository
public interface OperationLogDao extends JpaRepository<OperationLog, String> {
}