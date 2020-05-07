package cn.mir.snapped.goods.controller;

import cn.mir.common.utilities.ResponseResult;
import cn.mir.snapped.goods.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器-购买相关
 * <p>
 * Create time: 2020/3/27 01:16
 * </p>
 *
 * @author 周光兵
 */
@Slf4j
@RestController
public class PurchaseController {
    /**
     * 购买Service
     */
    private final PurchaseService purchaseService;

    /**
     * 构造方法注入对象
     *
     * @param purchaseService 购买Service
     */
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping(value = "/purchase")
    public ResponseResult<Void> purchase(Integer userId, Integer productId, Integer quantity) {
        log.info("执行采购");

        boolean flag = this.purchaseService.purchase(userId, productId, quantity);
        String message = flag ? "抢购成功" : "抢购失败";

        return new ResponseResult<>(flag, message);
    }
}
