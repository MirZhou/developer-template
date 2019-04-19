package cn.mir.background.management.repository;

import cn.mir.background.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao：用户
 * <p>Create time: 2019/4/10 9:26 AM</p>
 *
 * @author 周光兵
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
}