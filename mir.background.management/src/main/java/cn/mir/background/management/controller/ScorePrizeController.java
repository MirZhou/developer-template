package cn.mir.background.management.controller;

import cn.mir.background.management.dto.submit.ScorePrize;
import cn.mir.common.utilities.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Eros
 * @date 2022/1/25 21:26
 */
@Slf4j
@RestController
public class ScorePrizeController {
    @PostMapping("/prize")
    public ResponseResult<Void> addPrize(@Valid @RequestBody ScorePrize entity,
                                         BindingResult bindingResult) {
        log.info("message request data={}", entity);

        return new ResponseResult<>(true, null);
    }

//    @PostMapping("/prize")
//    public ResponseResult<Void> addPrize(@Valid @RequestBody ScorePrize entity,
//                                         BindingResult bindingResult) {
//        log.info("message request data={}", entity);
//
//        if (bindingResult.hasErrors()) {
//            bindingResult.getFieldErrors().forEach(fieldError -> {
//                log.warn("参数校验失败 filed:{} message:{}",
//                    fieldError.getField(),
//                    fieldError.getDefaultMessage());
//            });
//
//            // do something...
//        }
//
//        return new ResponseResult<>(true, null);
//    }
}
