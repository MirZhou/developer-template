package cn.mir.background.management.aspect.cud.repository;

import cn.mir.background.management.aspect.cud.domain.Action;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao：动作
 * <p>Create time: 2019/4/13 10:39 PM</p>
 *
 * @author 周光兵
 */
@Repository
public interface ActionDao extends MongoRepository<Action, String> {
}