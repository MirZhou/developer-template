package cn.eros.controller;

import cn.eros.dto.submit.LoginSubmit;
import cn.eros.error.ExceptionMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

/**
 * <p>create time：2020-08-07 09:37
 *
 * @author Eros
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public abstract class BaseControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SecurityManager securityManager;

    /**
     * 缺省用户名
     */
    private static final String DEFAULT_USERNAME = "admin";
    /**
     * 缺少密码
     */
    private static final String DEFAULT_PASSWORD = "password";

    private Subject subject;
    private MockHttpServletRequest mockHttpServletRequest;
    private MockHttpServletResponse mockHttpServletResponse;
    /**
     * 测试开始时间
     */
    private long startTime;

    protected abstract Object getController();

    @Before
    public void before() {
        log.info("开始测试");

        startTime = System.currentTimeMillis();

        mockHttpServletRequest = new MockHttpServletRequest(webApplicationContext.getServletContext());
        mockHttpServletResponse = new MockHttpServletResponse();
        MockHttpSession mockHttpSession = new MockHttpSession(webApplicationContext.getServletContext());
        mockHttpServletRequest.setSession(mockHttpSession);
        SecurityUtils.setSecurityManager(this.securityManager);

        this.login();
    }

    @After
    public void after() {
        // 耗时
        long duration = System.currentTimeMillis() - startTime;

        log.info("结束测试。 耗时：{}ms", duration);

        this.subject.logout();
    }

    public <T> T execution(MockHttpServletRequestBuilder mockHttp, TypeReference<T> type) throws Exception {
        mockHttp.header("token", this.subject.getSession().getId());
        mockHttp.contentType(MediaType.APPLICATION_JSON);

        // 记录请求发起时间
        long requestStartTime = System.currentTimeMillis();

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.getController())
                .setControllerAdvice(ExceptionMapper.class)
                .build()
                .perform(mockHttp);

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        String content = resultActions.andReturn().getResponse().getContentAsString();

        log.info("接口调用耗时：{}ms", (System.currentTimeMillis() - requestStartTime));

        return JSON.parseObject(content, type);
    }

    private void login() {
        String username = this.getUser().getUsername();
        String password = this.getUser().getPassword();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password, false);

        subject = new WebSubject.Builder(mockHttpServletRequest, mockHttpServletResponse)
                .buildWebSubject();
        subject.login(token);

        ThreadContext.bind(subject);
    }

    public LoginSubmit getUser() {
        return new LoginSubmit("admin", "password");
    }

    public Subject getSubject() {
        return this.subject;
    }
}
