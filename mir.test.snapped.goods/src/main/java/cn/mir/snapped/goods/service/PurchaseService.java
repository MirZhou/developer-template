package cn.mir.snapped.goods.service;

/**
 * Service接口：购买相关
 * <p>
 * Create time:2020-03-27 00:33:49
 * </p>
 * 
 * @author ErosZhou
 */
public interface PurchaseService {
    /**
     * 处理购买业务
     * 
     * @param userId    用户ID
     * @param productId 产品ID
     * @param quantity  购买数量
     * @return 成功 or 失败
     */
    boolean purchase(Integer userId, Integer productId, int quantity);
}