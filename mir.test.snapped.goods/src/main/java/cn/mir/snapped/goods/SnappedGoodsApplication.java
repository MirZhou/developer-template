package cn.mir.snapped.goods;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 抢购商品应用模块启动入口
 * <p>
 * Create time:2020-03-26 22:47:24
 * </p>
 * 
 * @author ErosZhou
 */
@SpringBootApplication
public class SnappedGoodsApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(SnappedGoodsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 执行完毕后，请注释以下代码。
//         this.generateArtifacts();
    }

    /**
     * 通过mybatisGenerator生成代码
     * 
     * @throws XMLParserException xml文件读取异常
     */
    @SuppressWarnings("unused")
    private void generateArtifacts()
            throws IOException, InvalidConfigurationException, SQLException, InterruptedException, XMLParserException {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(this.getClass().getResourceAsStream("/generatorConfig.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}