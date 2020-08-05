package cn.mir.spring.generator.mapper;

import cn.mir.spring.generator.entity.Product;
import cn.mir.spring.generator.enums.CategoryEnum;
import cn.mir.spring.generator.enums.MybatisEnumUtil;
import cn.mir.spring.generator.enums.ProductEnum;
import org.apache.commons.lang3.EnumUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void test() {
        for (int i =0;i < 20; i++) {
            String name = "商品" + (i + 1);
            String note = name + "描述";
            int stock = ThreadLocalRandom.current().nextInt(10, 1000);
            BigDecimal price = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.01, 100.00));
            ProductEnum status = MybatisEnumUtil.valueOf(ProductEnum.class, ThreadLocalRandom.current().nextInt(1, 4));
            CategoryEnum category = MybatisEnumUtil.valueOf(CategoryEnum.class, ThreadLocalRandom.current().nextInt(1, 5));

            Product product = new Product();
            product.setProductName(name);
            product.setStock(stock);
            product.setPrice(price);
            product.setVersion(1L);
            product.setNote(note);
            product.setStatus(status);
            product.setCategory(category);

            this.productMapper.insert(product);
        }

        this.list();
    }

    @Test
    public void list() {
        List<Product> list = this.productMapper.selectAll();

        list.forEach(System.out::println);
    }

}