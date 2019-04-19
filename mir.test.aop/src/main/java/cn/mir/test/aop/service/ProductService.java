package cn.mir.test.aop.service;

import cn.mir.test.aop.domain.Product;
import cn.mir.test.aop.security.AdminOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 服务：商品
 * <p>Create time: 2019/4/9 10:42 PM</p>
 *
 * @author 周光兵
 */
@Service
public class ProductService {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    /**
     * 新增商品信息
     * 权限：admin
     *
     * @param product 商品信息
     */
    @AdminOnly
    public void insert(Product product) {
        this.logger.info("新增商品 商品信息：" + product);
    }

    /**
     * 删除商品
     * 权限：admin
     *
     * @param id 商品ID
     */
    @AdminOnly
    public void delete(Integer id) {
        this.logger.info("删除商品 要删除商品ID：" + id);
    }

    /**
     * 获取商品
     * @return 商品
     */
    public Product get() {
        Product product = new Product();
        product.setId(35);
        product.setName("商品");

        return product;
    }
}