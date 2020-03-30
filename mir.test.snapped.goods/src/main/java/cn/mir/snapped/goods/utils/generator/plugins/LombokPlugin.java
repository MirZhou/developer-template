package cn.mir.snapped.goods.utils.generator.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * LombokPlugin
 * <p>
 * Create time: 2019-12-02 16:53
 * </p>
 *
 * @author 周光兵
 */
public class LombokPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // 导入Lombok包
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addImportedType("lombok.experimental.Accessors");

        // 添加lombok注解
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@Accessors(chain = true)");

        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
            IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 不生成getter
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
            IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 不生成setter
        return false;
    }
}
