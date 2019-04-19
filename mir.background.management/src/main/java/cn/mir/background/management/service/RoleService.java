package cn.mir.background.management.service;

import cn.mir.background.management.entity.Role;

/**
 * Service：角色
 * <p>Create time: 2019/4/12 10:45 PM</p>
 *
 * @author 周光兵
 */
public interface RoleService {
    /**
     * 保存记录
     * @param entity 要保存的记录
     */
    void save(Role entity);

    /**
     * 删除记录
     * @param entity 要删除的记录
     */
    void delete(Role entity);

    /**
     * 通过ID查询
     * @param id 要查询的ID值
     * @return 查询结果
     */
    Role getById(int id);
}