package generator;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

/**
 * 自定义数据库类型与JavaType类型映射
 * <p>Create time: 2019-12-03 15:26</p>
 *
 * @author 周光兵
 */
public class CustomizeJavaTypeResolver extends JavaTypeResolverDefaultImpl {
    public CustomizeJavaTypeResolver() {
        super();

        // TINYINT --> Integer
        super.typeMap.put(Types.TINYINT,
                new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
    }
}
