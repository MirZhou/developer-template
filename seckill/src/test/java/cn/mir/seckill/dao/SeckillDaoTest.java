package cn.mir.seckill.dao;

import cn.mir.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(SeckillDaoTest.class);

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void tesReduceNumberSuccess() {
        long id = 1000;
        LocalDateTime killTime = LocalDateTime.now();

        int count = this.seckillDao.reduceNumber(id, killTime);

        assertEquals(count,1 );
    }

    @Test
    public void tesReduceNumberByIdNotFoundSuccess() {
        long id = 1;
        LocalDateTime killTime = LocalDateTime.now();

        int count = this.seckillDao.reduceNumber(id, killTime);

        assertEquals(count,0);
    }

    @Test
    public void tesReduceNumberKillTimeInvalidSuccess() {
        long id = 1000;
        LocalDateTime killTime = LocalDateTime.now().plusDays(1);

        int count = this.seckillDao.reduceNumber(id, killTime);

        assertEquals(count,0);
    }

    @Test
    public void getById() {
        long id = 1000;

        Seckill seckill = this.seckillDao.getById(id);

        this.logger.info(seckill.toString());
    }

    @Test
    public void findAll() {
        List<Seckill> list = this.seckillDao.findAll(0, 100);

        for (Seckill seckill : list) {
            this.logger.info(seckill.toString());
        }
    }
}