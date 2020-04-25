package cn.mir.background.management.controller;

import cn.mir.background.management.error.ExceptionMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Controller测试基类
 * <p>Create time: 2019/4/23 4:47 PM</p>
 *
 * @author 周光兵
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public abstract class BaseControllerTest {
    /**
     * 测试开始时间
     */
    private long startTime;

    @Before
    public void setBefore() {
        log.info("开始测试");

        startTime = System.currentTimeMillis();
    }

    @After
    public void setAfter() {
        // 耗时
        long duration = System.currentTimeMillis() - startTime;

        log.info("结束测试。 耗时：{}ms", duration);
    }

    /**
     * 获取被触发的Controller对象
     *
     * @return Controller
     */
    protected abstract Object getController();

    /**
     * 执行测试
     *
     * @param mockHttp mockHttp对象
     * @param type     接口返回参数类型
     * @param <T>      接口返回参数类型的泛型对象
     * @return 接口返回参数
     * @throws Exception 调用异常
     */
    public <T> T execution(MockHttpServletRequestBuilder mockHttp, TypeReference<T> type) throws Exception {
        mockHttp.contentType(MediaType.APPLICATION_JSON);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.getController())
                .setControllerAdvice(ExceptionMapper.class)
                .build()
                .perform(mockHttp);

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        String content = resultActions.andReturn().getResponse().getContentAsString();

        return JSON.parseObject(content, type);
    }
}
