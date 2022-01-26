package cn.mir.background.management.controller;

import cn.mir.background.management.dto.submit.ScorePrize;
import cn.mir.background.management.dto.submit.ScorePrizeEvent;
import cn.mir.background.management.dto.submit.ScorePrizeEventUser;
import cn.mir.common.utilities.ResponseResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
public class ScorePrizeControllerTest extends BaseControllerTest {
    @Autowired
    private ScorePrizeController scorePrizeController;

    @Test
    public void addPrize() throws Exception {
        String urlTemplate = "/prize";

        // 请求参数对象
        ScorePrize submitData = new ScorePrize();
        log.info("message 第一次测试 data={}", submitData);

        TypeReference<ResponseResult<Void>> type = new TypeReference<ResponseResult<Void>>() {
        };

        MockHttpServletRequestBuilder mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(submitData));

        ResponseResult<Void> responseResult = super.execution(mockHttp, type);

        assertFalse(responseResult.getSuccess());

        //
        submitData = ScorePrize.builder()
            .prizeName("奖扣主题")
            .build();
        log.info("message 第二次测试 data={}", submitData);

        mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(submitData));

        responseResult = super.execution(mockHttp, type);

        Assert.assertFalse(responseResult.getSuccess());

        //
        submitData = ScorePrize.builder()
            .prizeName("奖扣主题")
            .prizeDate(LocalDate.now().plusDays(1L))
            .build();
        log.info("message 第三次测试 data={}", submitData);

        mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(submitData));

        responseResult = super.execution(mockHttp, type);

        Assert.assertFalse(responseResult.getSuccess());

        //
        submitData = ScorePrize.builder()
            .prizeName("奖扣主题")
            .prizeDate(LocalDate.now())
            .build();
        log.info("message 第四次测试 data={}", submitData);

        mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(submitData));

        responseResult = super.execution(mockHttp, type);

        Assert.assertFalse(responseResult.getSuccess());

        //
        submitData = ScorePrize.builder()
            .prizeName("奖扣主题")
            .prizeDate(LocalDate.now().minusDays(1L))
            .build();
        log.info("message 第五次测试 data={}", submitData);

        mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(submitData));

        responseResult = super.execution(mockHttp, type);

        Assert.assertFalse(responseResult.getSuccess());

        //
        submitData = ScorePrize.builder()
            .prizeName("奖扣主题")
            .prizeDate(LocalDate.now().minusDays(1L))
            .events(Collections.emptyList())
            .build();
        log.info("message 第六次测试 data={}", submitData);

        mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(submitData));

        responseResult = super.execution(mockHttp, type);

        Assert.assertFalse(responseResult.getSuccess());

        //
        submitData = ScorePrize.builder()
            .prizeName("奖扣主题")
            .prizeDate(LocalDate.now().minusDays(1L))
            .events(Collections.singletonList(ScorePrizeEvent.builder().build()))
            .build();
        log.info("message 第七次测试 data={}", submitData);

        mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(submitData));

        responseResult = super.execution(mockHttp, type);

        Assert.assertFalse(responseResult.getSuccess());

        //
        submitData = ScorePrize.builder()
            .prizeName("奖扣主题")
            .prizeDate(LocalDate.now().minusDays(1L))
            .events(Arrays.asList(
                ScorePrizeEvent.builder().build(),
                ScorePrizeEvent.builder().build()))
            .build();
        log.info("message 第八次测试 data={}", submitData);

        mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(submitData));

        responseResult = super.execution(mockHttp, type);

        Assert.assertFalse(responseResult.getSuccess());

        //
        submitData = ScorePrize.builder()
            .prizeName("奖扣主题")
            .prizeDate(LocalDate.now().minusDays(1L))
            .events(Collections.singletonList(ScorePrizeEvent.builder()
                .eventId(1L)
                .eventDate(LocalDate.now())
                .users(Collections.emptyList())
                .build()))
            .build();
        log.info("message 第九次测试 data={}", submitData);

        mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(submitData));

        responseResult = super.execution(mockHttp, type);

        Assert.assertFalse(responseResult.getSuccess());

        //
        List<ScorePrizeEventUser> eventUsers = Collections.singletonList(
            ScorePrizeEventUser.builder()
                .userId(1L)
                .scoreA(1)
                .scoreTypeA(null)
                .build());

        submitData = ScorePrize.builder()
            .prizeName("奖扣主题")
            .prizeDate(LocalDate.now().minusDays(1L))
            .events(Collections.singletonList(ScorePrizeEvent.builder()
                .eventId(1L)
                .eventDate(LocalDate.now())
                .users(eventUsers)
                .build()))
            .build();
        log.info("message 第十次测试 data={}", submitData);

        mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(submitData));

        responseResult = super.execution(mockHttp, type);

        assertFalse(responseResult.getSuccess());

        //
        eventUsers = Collections.singletonList(
            ScorePrizeEventUser.builder()
                .userId(1L)
                .scoreA(1)
                .scoreTypeA(0)
                .build());

        submitData = ScorePrize.builder()
            .prizeName("奖扣主题")
            .prizeDate(LocalDate.now().minusDays(1L))
            .events(Collections.singletonList(ScorePrizeEvent.builder()
                .eventId(1L)
                .eventDate(LocalDate.now())
                .users(eventUsers)
                .build()))
            .build();
        log.info("message 第十一次测试 data={}", submitData);

        mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(submitData));

        responseResult = super.execution(mockHttp, type);

        Assert.assertFalse(responseResult.getSuccess());

        //
        eventUsers = Collections.singletonList(
            ScorePrizeEventUser.builder()
                .userId(1L)
                .scoreA(1)
                .scoreTypeA(1)
                .build());

        submitData = ScorePrize.builder()
            .prizeName("奖扣主题")
            .prizeDate(LocalDate.now().minusDays(1L))
            .events(Collections.singletonList(ScorePrizeEvent.builder()
                .eventId(1L)
                .eventDate(LocalDate.now())
                .users(eventUsers)
                .build()))
            .build();
        log.info("message 第12次测试 data={}", submitData);

        mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(submitData));

        responseResult = super.execution(mockHttp, type);

        assertTrue(responseResult.getSuccess());

        //
        eventUsers = Collections.singletonList(
            ScorePrizeEventUser.builder()
                .userId(1L)
                .scoreA(1)
                .scoreTypeA(1)
                .opScore(30)
                .build());

        submitData = ScorePrize.builder()
            .prizeName("奖扣主题")
            .prizeDate(LocalDate.of(2018, 12, 31))
            .events(Collections.singletonList(ScorePrizeEvent.builder()
                .eventId(1L)
                .eventDate(LocalDate.now())
                .users(eventUsers)
                .build()))
            .build();
        log.info("message 第13次测试 data={}", submitData);

        mockHttp = MockMvcRequestBuilders.post(urlTemplate);
        mockHttp.content(JSON.toJSONString(submitData));

        responseResult = super.execution(mockHttp, type);

        assertFalse(responseResult.getSuccess());


    }

    @Override
    protected Object getController() {
        return this.scorePrizeController;
    }
}