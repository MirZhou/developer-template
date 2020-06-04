package cn.mir.background.management.mongodb;

import cn.mir.background.management.aspect.cud.repository.ActionDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Create time: 2020-06-04 10:06</p>
 *
 * @author 周光兵
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActionDaoTest {
    @Autowired
    private ActionDao actionDao;

    @Test
    public void testFindByObjectId() {
        this.actionDao.findByObjectId("2").forEach(System.out::println);
    }
}
