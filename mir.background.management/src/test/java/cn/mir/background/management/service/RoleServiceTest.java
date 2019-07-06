package cn.mir.background.management.service;

import cn.mir.background.management.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    @Test
    public void testInsert() {
        Role role = new Role();
        role.setCode("admin");
        role.setName("测试");
        role.setMarkEnable(1);
        role.setRoleType(1);
        role.setDescription("测试测试");
        role.setMarkDelete(0);
        role.setCreateUserId("test admin");
        role.setCreateTime(LocalDateTime.now());

        this.roleService.save(role);
    }

    @Test
    public void testUpdate() {
        Role role = this.roleService.getById(5);
        role.setName("修改测试");
        role.setDescription("修改5-1");
        role.setModifyTime(LocalDateTime.now());

        this.roleService.save(role);
    }

    @Test
    public void testDelete() {
        Role role = new Role();
        role.setId(4);

        this.roleService.delete(role);
    }
}