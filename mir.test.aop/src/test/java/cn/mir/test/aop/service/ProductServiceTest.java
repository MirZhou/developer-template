package cn.mir.test.aop.service;

import cn.mir.test.aop.domain.Product;
import cn.mir.test.aop.security.CurrentUserHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试类：商品服务
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(ProductServiceTest.class);

    @Autowired
    private ProductService productService;

    /**
     * 测试：匿名插入商品
     */
    @Test(expected = Exception.class)
    public void testAnonymousInsert() {
        CurrentUserHolder.set("Tom");

        Product product = new Product();
        product.setId(1);
        product.setName("口香糖");

        this.productService.insert(product);
    }

    /**
     * 测试：admin用户插入商品信息
     */
    @Test
    public void testAdminInsert() {
        CurrentUserHolder.set("admin");

        Product product = new Product();
        product.setId(1);
        product.setName("口香糖");

        this.productService.insert(product);
    }

    /**
     * 测试：匿名删除商品
     */
    @Test(expected = Exception.class)
    public void testAnonymousDelete() {
        CurrentUserHolder.set("Tom");

        this.productService.delete(1);
    }

    /**
     * 测试：admin用户删除商品
     */
    @Test
    public void testAdminDelete() {
        CurrentUserHolder.set("admin");

        this.productService.delete(1);
    }

    /**
     * 测试：获取商品
     */
    @Test
    public void testGet() {
        Product product = this.productService.get();

        this.logger.info("执行结果：" + product);
    }
}