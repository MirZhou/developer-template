package cn.mir.background.management.controller;

import cn.mir.common.utilities.ResponseResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 */
public class UserControllerTest extends BaseControllerTest {
    @Autowired
    private UserController userController;

    @Test
    public void save() throws Exception {
        String urlTemplate = "/user";

        // 第一次尚义
        // 请求参数对象
        JSONObject requestParam = new JSONObject();

        TypeReference<ResponseResult<Void>> type = new TypeReference<ResponseResult<Void>>() {
        };

        MockHttpServletRequestBuilder mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(requestParam));

        ResponseResult<Void> responseResult = super.execution(mockHttp, type);

        Assert.assertNotNull(responseResult);
        Assert.assertFalse(responseResult.getSuccess());
        Assert.assertEquals("姓名不能为空", responseResult.getMessage());

        // 第二次测试
        requestParam.clear();
        requestParam.put("username", "eros");

        mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(requestParam));

        responseResult = super.execution(mockHttp, type);

        Assert.assertNotNull(responseResult);
        Assert.assertFalse(responseResult.getSuccess());
        Assert.assertEquals("昵称不能为空", responseResult.getMessage());

        // 第三次测试
        requestParam.clear();
        requestParam.put("username", "eros");
        requestParam.put("nickname", "eros");
        requestParam.put("phone", "18684107874");

        mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(requestParam));

        responseResult = super.execution(mockHttp, type);

        Assert.assertNotNull(responseResult);
        Assert.assertFalse(responseResult.getSuccess());
        Assert.assertEquals("性别不能为空", responseResult.getMessage());

        // 第四次测试
        requestParam.clear();
        requestParam.put("username", "eros");
        requestParam.put("nickname", "eros");
        requestParam.put("gender", "男");
        requestParam.put("phone", "18684107874");

        mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(requestParam));

        responseResult = super.execution(mockHttp, type);

        Assert.assertNotNull(responseResult);
        Assert.assertFalse(responseResult.getSuccess());
        Assert.assertEquals("住址不能为空", responseResult.getMessage());
    }

    @Override
    protected Object getController() {
        return this.userController;
    }
}