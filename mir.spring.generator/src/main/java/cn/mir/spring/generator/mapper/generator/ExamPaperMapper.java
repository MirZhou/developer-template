package cn.mir.spring.generator.mapper.generator;

import cn.mir.spring.generator.entity.ExamPaper;
import cn.mir.spring.generator.entity.ExamPaperExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

public interface ExamPaperMapper {
    long countByExample(ExamPaperExample example);

    int deleteByExample(ExamPaperExample example);

    @Delete({
        "delete from t_exam_paper",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_exam_paper (exam_type_id, name, ",
        "total_score, category)",
        "values (#{examTypeId,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{totalScore,jdbcType=DECIMAL}, #{category,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(ExamPaper record);

    int insertSelective(ExamPaper record);

    List<ExamPaper> selectByExampleWithRowbounds(ExamPaperExample example, RowBounds rowBounds);

    List<ExamPaper> selectByExample(ExamPaperExample example);

    @Select({
        "select",
        "id, exam_type_id, name, total_score, category",
        "from t_exam_paper",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("cn.mir.spring.generator.mapper.generator.ExamPaperMapper.BaseResultMap")
    ExamPaper selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExamPaper record, @Param("example") ExamPaperExample example);

    int updateByExample(@Param("record") ExamPaper record, @Param("example") ExamPaperExample example);

    int updateByPrimaryKeySelective(ExamPaper record);

    @Update({
        "update t_exam_paper",
        "set exam_type_id = #{examTypeId,jdbcType=BIGINT},",
          "name = #{name,jdbcType=VARCHAR},",
          "total_score = #{totalScore,jdbcType=DECIMAL},",
          "category = #{category,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ExamPaper record);
}