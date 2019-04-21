package cn.mir.seckill.controller;

import cn.mir.seckill.dto.Exposer;
import cn.mir.seckill.dto.SecKillExecution;
import cn.mir.seckill.dto.SeckillResult;
import cn.mir.seckill.entity.Seckill;
import cn.mir.seckill.enums.SecKillStateEnum;
import cn.mir.seckill.exception.RepeatKillException;
import cn.mir.seckill.exception.SecKillClosedException;
import cn.mir.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * <p>Create time: 2019/4/20 5:36 PM</p>
 *
 * @author 周光兵
 */
@Controller
@RequestMapping("/seckill")
public class SecKillController {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 秒杀商品Service
     */
    private final SeckillService seckillService;

    /**
     * 构造方法注入对象
     *
     * @param seckillService 秒杀商品Service
     */
    @Autowired
    public SecKillController(SeckillService seckillService) {
        this.seckillService = seckillService;
    }

    @GetMapping(value = "/list")
    public String list(Model model) {
        this.logger.info("获取秒杀商品列表数据");

        List<Seckill> list = this.seckillService.findSeckillList();

        // 获取列表数据并绑定到model中
        model.addAttribute("list", list);

        return "/seckill/list";
    }

    @GetMapping(value = "/{seckillId}/detail")
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        this.logger.info("查看秒杀对象详情 seckillId:{}", seckillId);

        if (seckillId == null) {
            this.logger.error("seckillId is null");

            return "redirect:/seckill/list";
        }

        // 获取秒杀对象
        Seckill seckill = this.seckillService.getByid(seckillId);

        if (seckill == null) {
            this.logger.error("seckill is null");

            return "forward:/seckill/list";
        }

        model.addAttribute("seckill", seckill);
        model.addAttribute("startTimeMillis", seckill.getStartTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        model.addAttribute("endTimeMillis", seckill.getEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());

        return "/seckill/detail";
    }

    @PostMapping(value = "/{seckillId}/exposer", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> result;

        try {
            Exposer exposer = this.seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<>(true, exposer);
        } catch (Exception ex) {
            this.logger.error(ex.getMessage(), ex);

            result = new SeckillResult<Exposer>(false, ex.getMessage());
        }


        return result;
    }

    @PostMapping(value = "/{seckillId}/{md5}/execution", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SeckillResult<SecKillExecution> execute(@PathVariable("seckillId") Long seckillId, @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killPhone", required = false) String killPhone) {
        if (killPhone == null) {
            return new SeckillResult<>(false, "未注册");
        }

        try {

            SecKillExecution secKillExecution = this.seckillService.executeSecKillProcedure(seckillId, killPhone, md5);
            return new SeckillResult<>(true, secKillExecution);

        }  catch (RepeatKillException ex) {
            this.logger.error(ex.getMessage(), ex);

            SecKillExecution secKillExecution = new SecKillExecution(seckillId, SecKillStateEnum.REPEAT_KILL);

            return new SeckillResult<>(true, secKillExecution);
        } catch (SecKillClosedException ex) {
            this.logger.error(ex.getMessage(), ex);

            SecKillExecution secKillExecution = new SecKillExecution(seckillId, SecKillStateEnum.END);

            return new SeckillResult<>(true, secKillExecution);
        }catch (Exception ex) {
            this.logger.error(ex.getMessage(), ex);

            SecKillExecution secKillExecution = new SecKillExecution(seckillId, SecKillStateEnum.INNER_ERROR);

            return new SeckillResult<>(true, secKillExecution);
        }
    }

    @GetMapping(value = "/time/now")
    @ResponseBody
    public SeckillResult<Long> time() {
        return new SeckillResult<>(true, LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

}