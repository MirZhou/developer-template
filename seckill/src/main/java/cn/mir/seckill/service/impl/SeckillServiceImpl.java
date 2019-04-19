package cn.mir.seckill.service.impl;

import cn.mir.seckill.dao.SeckillDao;
import cn.mir.seckill.dao.SuccessKilledDao;
import cn.mir.seckill.dto.Exposer;
import cn.mir.seckill.dto.SecKillExecution;
import cn.mir.seckill.entity.Seckill;
import cn.mir.seckill.entity.SuccessKilled;
import cn.mir.seckill.enums.SecKillStateEnum;
import cn.mir.seckill.exception.RepeatKillException;
import cn.mir.seckill.exception.SecKillClosedException;
import cn.mir.seckill.exception.SecKillException;
import cn.mir.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 业务接口实现：秒杀商品
 * <p>Create time: 2019/4/16 10:05 PM</p>
 *
 * @author 周光兵
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 秒杀商品Dao
     */
    private final SeckillDao seckillDao;
    /**
     * 成功秒杀记录Dao
     */
    private final SuccessKilledDao successKilledDao;

    /**
     * 构造方法注入对象
     *
     * @param seckillDao       秒杀商品Dao
     * @param successKilledDao 成功秒杀记录Dao
     */
    @Autowired
    public SeckillServiceImpl(SeckillDao seckillDao, SuccessKilledDao successKilledDao) {
        this.seckillDao = seckillDao;
        this.successKilledDao = successKilledDao;
    }

    @Override
    public List<Seckill> findSeckillList() {
        return this.seckillDao.findAll(0, 100);
    }

    @Override
    public Seckill getByid(long seckillId) {
        return this.seckillDao.getById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = this.seckillDao.getById(seckillId);

        if (seckill == null) {
            return new Exposer(false, seckillId);
        }

        LocalDateTime startTime = seckill.getStartTime();
        LocalDateTime endTime = seckill.getEndTime();
        LocalDateTime now = LocalDateTime.now();

        if (startTime.compareTo(now) > 0 || endTime.compareTo(now) < 0) {
            // 开始时间大于当前时间或者结束时间小于当前时间
            return new Exposer(false, seckillId, now, startTime, endTime);
        }

        // 生成MD5验证字符串
        String md5 = this.getMD5(seckillId);

        return new Exposer(true, md5, seckillId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SecKillExecution executeSecKill(long secKillId, String userPhone, String md5)
            throws SecKillException, RepeatKillException, SecKillClosedException {
        // 校验MD5值
        if (md5 == null || !md5.equals(this.getMD5(secKillId))) {
            throw new SecKillException("sec kill data rewrite");
        }

        // 执行秒杀逻辑
        try {

            // 减库存
            int updateCount = this.seckillDao.reduceNumber(secKillId, LocalDateTime.now());

            if (updateCount < 1) {
                // 未成功更新库存
                throw new SecKillClosedException("sec kill is closed.");
            }

            // 记录购买行为
            SuccessKilled successKilled = new SuccessKilled();
            successKilled.setState(0);
            successKilled.setSeckillId(secKillId);
            successKilled.setUserPhone(userPhone);

            // 插入行数
            int insertCount = this.successKilledDao.insertSuccessKilled(successKilled);

            if (insertCount < 1) {
                throw new RepeatKillException("sec kill repeated");
            }

            // 从数据库查询
            successKilled = this.successKilledDao.getByIdWithSeckill(secKillId, userPhone);

            // 返回对象
            return new SecKillExecution(secKillId, SecKillStateEnum.SUCCESS, successKilled);

        } catch (SecKillClosedException ex1) {
            throw ex1;
        } catch (RepeatKillException ex2) {
            throw ex2;
        } catch (Exception ex3) {
            this.logger.error(ex3.getMessage(), ex3);

            // 所有编译期异常转换为运行期异常
            throw new SecKillException("sec kill inner error:" + ex3.getMessage());
        }
    }

    /**
     * 生成MD5验证字符串并返回
     *
     * @param secKillId 要加密的字符串
     * @return 加密后字符串
     */
    private String getMD5(long secKillId) {
        // MD5盐值字符串，用于混淆MD5
        String slat = "fdsalkfu94823&*(%#&U#KL@#89fdslkafd*(h54k3q3";
        String base = secKillId + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
