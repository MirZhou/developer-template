package cn.mir.snapped.goods.service.impl;

import cn.mir.snapped.goods.entity.Product;
import cn.mir.snapped.goods.entity.PurchaseRecord;
import cn.mir.snapped.goods.mapper.ProductMapper;
import cn.mir.snapped.goods.mapper.PurchaseRecordMapper;
import cn.mir.snapped.goods.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Service接口实现：购买相关
 * <p>
 * Create time:2020-03-27 00:35:38
 * </p>
 *
 * @author ErosZhou
 */
@Slf4j
@Service
public class PurchaseServiceImpl implements PurchaseService {
    /**
     * 产品Mapper
     */
    private final ProductMapper productMapper;
    /**
     * 购买记录Mapper
     */
    private final PurchaseRecordMapper purchaseRecordMapper;

    /**
     * 构造方法注入对象
     *
     * @param productMapper        产品Mapper
     * @param purchaseRecordMapper 购买记录Mapper
     */
    public PurchaseServiceImpl(ProductMapper productMapper, PurchaseRecordMapper purchaseRecordMapper) {
        this.productMapper = productMapper;
        this.purchaseRecordMapper = purchaseRecordMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean purchase(Integer userId, Integer productId, int quantity) {
        // 获取产品
        Product product = this.productMapper.selectByPrimaryKey(productId);

        if (product == null) {
            return false;
        }

        // 比较库存和购买数量
        if (product.getStock() < quantity) {
            // 库存不足
            return false;
        }

        // 扣减库存
        this.productMapper.decreaseProduct(productId, quantity);

        // 初始化购买记录
        PurchaseRecord entity = this.initPurchaseRecord(userId, product, quantity);
        // 插入购买记录
        this.purchaseRecordMapper.insert(entity);

        return true;
    }

    private PurchaseRecord initPurchaseRecord(Integer userId, Product product, int quantity) {
        // 计算总价
        BigDecimal sum = product.getPrice().multiply(new BigDecimal(quantity));

        return new PurchaseRecord()
                .setNote("购买日志，时间" + System.currentTimeMillis())
                .setPrice(product.getPrice())
                .setProductId(product.getId())
                .setQuantity(quantity)
                .setSum(sum)
                .setUserId(userId);
    }

}