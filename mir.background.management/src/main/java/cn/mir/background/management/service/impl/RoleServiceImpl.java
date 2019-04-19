package cn.mir.background.management.service.impl;

import cn.mir.background.management.entity.Role;
import cn.mir.background.management.repository.RoleDao;
import cn.mir.background.management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Service实现：角色
 * <p>Create time: 2019/4/12 10:47 PM</p>
 *
 * @author 周光兵
 */
@Service
public class RoleServiceImpl implements RoleService {
    /**
     * 角色Dao
     */
    private final RoleDao roleDao;

    /**
     * 构造方法注入对象
     *
     * @param roleDao 角色Dao
     */
    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void save(Role entity) {
        this.roleDao.saveAndFlush(entity);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void delete(Role entity) {
        this.roleDao.delete(entity);
    }

    @Override
    public Role getById(int id) {
        return this.roleDao.findById(id).orElse(null);
    }
}