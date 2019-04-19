package cn.mir.seckill.dao;

import cn.mir.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(SuccessKilledDaoTest.class);

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {
        SuccessKilled successKilled = new SuccessKilled();
        successKilled.setSeckillId(1001L);
        successKilled.setUserPhone("18684107875");
        successKilled.setState(0);

        int count = this.successKilledDao.insertSuccessKilled(successKilled);

        assertEquals(count, 1);
    }

    @Test
    public void getByIdWithSeckill() {
        long id = 1001L;
        String userPhone = "18684107875";

        SuccessKilled successKilled = this.successKilledDao.getByIdWithSeckill(id, userPhone);

        if (successKilled != null) {
            this.logger.info(successKilled.toString());
        } else {
            this.logger.info("未找到对应数据");
        }
    }
}