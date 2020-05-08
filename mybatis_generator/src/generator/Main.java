package generator;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 主类
 * <p>
 * Create time:2020-05-08 11:42:05
 * </p>
 * 
 * @author Eros Zhou
 */
public class Main {
    public static void main(String[] args) {
        try {
            List<String> warnings = new ArrayList<>();

            File configFile = new File("generatorConfig.xml");

            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);

            // 获取配置上下文
            Context context = config.getContext("mysql");

            // 写入自定义配置
            addPluginConfiguration(context);
            setCommentGeneratorConfiguration(context);
            setJavaTypeResolverConfiguration(context);

            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * 添加自定义插件配置
     *
     * @param context 配置上下文
     */
    private static void addPluginConfiguration(Context context) {
        // 创建自定义插件配置
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        // 自定义处理类
        pluginConfiguration.setConfigurationType("generator.LombokPlugin");
        // using lombok
        pluginConfiguration.addProperty("hasLombok", "true");

        // 写入到配置中
        context.addPluginConfiguration(pluginConfiguration);
    }

    /**
     * 添加自定义注释的配置
     *
     * @param context 配置上下文
     */
    private static void setCommentGeneratorConfiguration(Context context) {
        // 创建注释配置
        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        // 自定义处理类
        commentGeneratorConfiguration.setConfigurationType("generator.MySqlCommentGenerator");
        // 是否不生成日期
        commentGeneratorConfiguration.addProperty("suppressDate", "false");
        // 是否不生成注释
        commentGeneratorConfiguration.addProperty("suppressAllComments", "true");
        // 是否添加数据库内的注释
        commentGeneratorConfiguration.addProperty("addRemarkComments", "true");
        // 作者
        commentGeneratorConfiguration.addProperty("author", "eros");
        // 日期格式
        commentGeneratorConfiguration.addProperty("dateFormat", "yyyy-MM-dd HH:mm:ss");

        // 添加自定义配置
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
    }

    /**
     * 添加自定义JavaType配置
     *
     * @param context 配置上下文
     */
    private static void setJavaTypeResolverConfiguration(Context context) {
        // 创建自定义JavaType配置
        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
        // 自定义处理类
        javaTypeResolverConfiguration.setConfigurationType("generator.CustomizeJavaTypeResolver");
        javaTypeResolverConfiguration.addProperty("forceBigDecimals", "true");
        javaTypeResolverConfiguration.addProperty("useJSR310Types", "true");

        // 添加自定义配置
        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
    }
}