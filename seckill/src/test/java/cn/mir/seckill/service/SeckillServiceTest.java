package cn.mir.seckill.service;

import cn.mir.seckill.dto.Exposer;
import cn.mir.seckill.dto.SecKillExecution;
import cn.mir.seckill.entity.Seckill;
import cn.mir.seckill.exception.RepeatKillException;
import cn.mir.seckill.exception.SecKillClosedException;
import cn.mir.seckill.exception.SecKillException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 秒杀业务
     */
    @Autowired
    private SeckillService seckillService;

    @Test
    public void findSeckillList() {
        List<Seckill> list = this.seckillService.findSeckillList();

        this.logger.info("list={}", list);
    }

    @Test
    public void getByid() {
        Seckill seckill = this.seckillService.getByid(1000L);

        this.logger.info("seckill={}", seckill);
    }

    @Test
    public void testSecKillLogic() {
        Long id = 100L;
        Exposer exposer = this.seckillService.exportSeckillUrl(id);

        if (exposer.isExposed()) {
            this.logger.info("exposer={}", exposer);

            String phone = "18684107874";
            String md5 = exposer.getMd5();

            try {
                SecKillExecution secKillExecution = this.seckillService.executeSecKill(id, phone, md5);

                this.logger.info("secKillExecution={}", secKillExecution);
            } catch (RepeatKillException e) {
                this.logger.error(e.getMessage());
            } catch (SecKillClosedException e) {
                this.logger.error(e.getMessage());
            } catch (SecKillException e) {
                this.logger.error(e.getMessage());
            }
        } else {
            // 秒杀未开启
            this.logger.warn("exposer={}", exposer);
        }
    }
}