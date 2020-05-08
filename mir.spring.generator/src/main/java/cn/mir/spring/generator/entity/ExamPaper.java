package cn.mir.spring.generator.entity;

import cn.mir.spring.generator.enums.ExamPaperCategoryEnum;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 试卷表
 * <p>Table name:t_exam_paper</p>
 * <p>Create time:2020-05-08 11:00:00</p>
 *
 * @author eros
 */
@Data
public class ExamPaper implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 考点id
     */
    private Long examTypeId;

    /**
     * 试卷名称
     */
    private String name;

    /**
     * 总分
     */
    private BigDecimal totalScore;

    /**
     * 试卷分类。参见枚举：cn.mir.spring.generator.enums.ExamPaperCategoryEnum
     */
    private ExamPaperCategoryEnum category;

    private static final long serialVersionUID = 1L;

    public ExamPaper withId(Long id) {
        this.setId(id);
        return this;
    }

    public ExamPaper withExamTypeId(Long examTypeId) {
        this.setExamTypeId(examTypeId);
        return this;
    }

    public ExamPaper withName(String name) {
        this.setName(name);
        return this;
    }

    public ExamPaper withTotalScore(BigDecimal totalScore) {
        this.setTotalScore(totalScore);
        return this;
    }

    public ExamPaper withCategory(ExamPaperCategoryEnum category) {
        this.setCategory(category);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", examTypeId=").append(examTypeId);
        sb.append(", name=").append(name);
        sb.append(", totalScore=").append(totalScore);
        sb.append(", category=").append(category);
        sb.append("]");
        return sb.toString();
    }
}