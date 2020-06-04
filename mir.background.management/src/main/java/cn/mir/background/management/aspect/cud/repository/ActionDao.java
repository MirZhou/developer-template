package cn.mir.background.management.aspect.cud.repository;

import cn.mir.background.management.aspect.cud.domain.Action;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Dao：动作
 * <p>Create time: 2019/4/13 10:39 PM</p>
 *
 * @author 周光兵
 */
@Repository
public interface ActionDao extends MongoRepository<Action, String> {
    /**
     * 查询
     *
     * @param objectId 对象ID
     * @return 查询结果
     */
    List<Action> findByObjectId(String objectId);
}