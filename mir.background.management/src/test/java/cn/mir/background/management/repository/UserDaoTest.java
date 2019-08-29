package cn.mir.background.management.repository;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.mir.background.management.entity.User;

/**
 * 测试类：用户Dao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    /**
     * 测试方法：插入管理员用户
     */
    @Test
    public void testInsertAdminUser() {
        User entity = new User();
        entity.setCode("administrator");
        entity.setUsername("administrator");
        entity.setRealName("超级管理员");
        entity.setPassword("123456");
        entity.setLoginCount(0);
        entity.setIsStaff(1);
        entity.setMarkEnable(1);
        entity.setUserSource(1);
        entity.setDescription("超级管理员");
        entity.setMarkDelete(0);
        entity.setCreateTime(LocalDateTime.now());

        // 执行保存
        this.userDao.saveAndFlush(entity);

        this.logger.info("保存用户-->{}", entity);
    }
}