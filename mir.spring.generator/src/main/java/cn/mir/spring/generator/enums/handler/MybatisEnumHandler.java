package cn.mir.spring.generator.enums.handler;

import cn.mir.spring.generator.enums.IMybatisEnum;
import cn.mir.spring.generator.enums.MybatisEnumUtil;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mybatis枚举处理器
 * <p>create time：2020-08-04 19:04
 *
 * @author Eros
 */
@MappedTypes(IMybatisEnum.class)
public class MybatisEnumHandler<E extends Enum<?> & IMybatisEnum> implements TypeHandler<IMybatisEnum> {
    private final Class<E> cls;

    public MybatisEnumHandler(Class<E> cls) {
        this.cls = cls;
    }


    @Override
    public void setParameter(PreparedStatement ps, int i, IMybatisEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public IMybatisEnum getResult(ResultSet rs, String columnName) throws SQLException {
        int id = rs.getInt(columnName);
        return MybatisEnumUtil.valueOf(cls, id);
    }

    @Override
    public IMybatisEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        int id = rs.getInt(columnIndex);
        return MybatisEnumUtil.valueOf(cls, id);
    }

    @Override
    public IMybatisEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int id = cs.getInt(columnIndex);
        return MybatisEnumUtil.valueOf(cls, id);
    }
}
