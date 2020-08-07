package cn.eros.controller;

import cn.mir.common.utilities.ResponseResult;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AccountControllerTest extends BaseControllerTest {
    @Autowired
    private AccountController accountController;

    @Test
    public void getCurrentUsername() throws Exception {
        String urlTemplate = "/current/username";

        TypeReference<ResponseResult<?>> type = new TypeReference<ResponseResult<?>>() {
        };

        MockHttpServletRequestBuilder mockHttp = MockMvcRequestBuilders.get(urlTemplate);

        ResponseResult<?> responseResult = super.execution(mockHttp, type);

        Assert.assertNotNull(responseResult);
        Assert.assertTrue(responseResult.getSuccess());
        Assert.assertEquals(getUser().getUsername(), responseResult.getData());
    }

    @Override
    protected Object getController() {
        return this.accountController;
    }
}