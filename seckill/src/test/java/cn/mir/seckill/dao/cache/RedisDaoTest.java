package cn.mir.seckill.dao.cache;

import cn.mir.seckill.dao.SeckillDao;
import cn.mir.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void testSeckill() {
        long id = 1000L;

        Seckill seckill = this.redisDao.getSeckill(id);

        if (seckill == null) {
            seckill = this.seckillDao.getById(id);

            if (seckill != null) {
                String result = this.redisDao.putSeckill(seckill);

                this.logger.info(result);

                this.logger.info("cache seckill:{}", this.redisDao.getSeckill(id));
            }
        } else {
            this.logger.info("cache seckill:{}", seckill);
        }
    }
}