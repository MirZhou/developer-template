package cn.eros.controller;

import cn.eros.error.ExceptionMapper;
import cn.mir.common.utilities.ResponseResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AccountControllerTest {
    private static final String USERNAME = "admin";

    @Autowired
    private AccountController accountController;

    @Autowired
    private SecurityManager securityManager;

    @Before
    public void before() {
        SecurityUtils.setSecurityManager(this.securityManager);
    }

    @Test
    public void testLogin() throws Exception {
        String urlTemplate = "/login";

        // 请求参数对象
        JSONObject requestParam = new JSONObject();
        requestParam.put("username", USERNAME);
        requestParam.put("password", "password");

        MockHttpServletRequestBuilder mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.contentType(MediaType.APPLICATION_JSON);
        mockHttp.content(JSON.toJSONString(requestParam));

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.accountController)
                .build()
                .perform(mockHttp);

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        String content = resultActions.andReturn().getResponse().getContentAsString();

        TypeReference<ResponseResult<?>> type = new TypeReference<ResponseResult<?>>() {
        };
        ResponseResult<?> responseResult = JSON.parseObject(content, type);

        Assert.assertNotNull(responseResult);
        Assert.assertTrue(responseResult.getSuccess());

        getCurrentUsername(responseResult.getData().toString());
    }

    @Test
    public void getCurrentUsername() throws Exception {
        this.getCurrentUsername("e0928897-a94a-44a6-a765-72312ae6dbf0");
    }

    private void getCurrentUsername(String token) throws Exception {
        String urlTemplate = "/current/username";

        MockHttpServletRequestBuilder mockHttp = MockMvcRequestBuilders.get(urlTemplate);
        mockHttp.contentType(MediaType.APPLICATION_JSON);
        mockHttp.header("token", token);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.accountController)
                .setControllerAdvice(ExceptionMapper.class)
                .build()
                .perform(mockHttp);

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        String content = resultActions.andReturn().getResponse().getContentAsString();

        TypeReference<ResponseResult<?>> type = new TypeReference<ResponseResult<?>>() {
        };
        ResponseResult<?> responseResult = JSON.parseObject(content, type);

        Assert.assertNotNull(responseResult);
        Assert.assertTrue(responseResult.getSuccess());
        Assert.assertEquals(USERNAME, responseResult.getData());
    }
}