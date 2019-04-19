package cn.mir.background.management.repository;

import cn.mir.background.management.entity.DataDictionaryOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao：数据字典选项
 * <p>Create time: 2019/4/10 9:06 AM</p>
 *
 * @author 周光兵
 */
@Repository
public interface DataDictionaryOptionDao extends JpaRepository<DataDictionaryOption, Integer> {
}