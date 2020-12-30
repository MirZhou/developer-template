package cn.mir.background.management.service.mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import cn.mir.background.management.entity.Role;
import cn.mir.background.management.repository.RoleDao;
import cn.mir.background.management.service.RoleService;
import cn.mir.background.management.service.impl.RoleServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * Mock单元测试：角色Service
 * 
 * @author 周光兵
 * @date 2020/12/30 17:36
 */
@Slf4j
public class RoleServiceMockTest {
    @Test
    public void getById() {
        RoleDao roleDao = mock(RoleDao.class);

        // 注入空数据
        when(roleDao.findById(1)).thenReturn(Optional.empty());

        RoleService roleService = new RoleServiceImpl(roleDao);

        Role role = roleService.getById(1);

        Assert.assertNull(role);

        Role mockRole = new Role();
        mockRole.setId(2);

        // 注入模拟的角色数据
        when(roleDao.findById(mockRole.getId())).thenReturn(Optional.of(mockRole));

        role = roleService.getById(mockRole.getId());

        Assert.assertNotNull(role);
        Assert.assertEquals(mockRole.getId(), role.getId());
        log.info("role:{}", role);
    }
}
