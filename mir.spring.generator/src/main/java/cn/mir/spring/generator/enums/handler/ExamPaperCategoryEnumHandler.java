package cn.mir.spring.generator.enums.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.mir.spring.generator.enums.ExamPaperCategoryEnum;

/**
 * 枚举类型数据处理：试卷分类
 * <p>
 * Create time:2020-05-08 10:53:54
 * </p>
 * 
 * @author Eros Zhou
 */
@MappedTypes(ExamPaperCategoryEnum.class)
public class ExamPaperCategoryEnumHandler implements TypeHandler<ExamPaperCategoryEnum> {
    @Override
    public void setParameter(PreparedStatement ps, int i, ExamPaperCategoryEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getStatus());
    }

    @Override
    public ExamPaperCategoryEnum getResult(ResultSet rs, String columnName) throws SQLException {
        int id = rs.getInt(columnName);

        return ExamPaperCategoryEnum.statusOf(id);
    }

    @Override
    public ExamPaperCategoryEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        int id = rs.getInt(columnIndex);
        return ExamPaperCategoryEnum.statusOf(id);
    }

    @Override
    public ExamPaperCategoryEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int id = cs.getInt(columnIndex);
        return ExamPaperCategoryEnum.statusOf(id);
    }

}