package cn.mir.spring.generator.mapper;

import java.util.List;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.mir.spring.generator.entity.ExamPaper;
import cn.mir.spring.generator.entity.ExamPaperExample;
import cn.mir.spring.generator.enums.ExamPaperCategoryEnum;

/**
 * 测试类：用户Dao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamPaperMapperTest {
    @Autowired
    private ExamPaperMapper examPaperMapper;

    @Transactional
    @Test
    public void testInsert() {
        ExamPaper entity = new ExamPaper().withExamTypeId(1L).withName("测试").withCategory(ExamPaperCategoryEnum.NORMAL)
                .withTotalScore(new BigDecimal(100));

        this.examPaperMapper.insert(entity);

        Assert.assertTrue(entity.getId() > 0L);

        List<ExamPaper> entities = this.examPaperMapper.selectByExample(new ExamPaperExample());
        entities.forEach(System.out::println);
    }
}