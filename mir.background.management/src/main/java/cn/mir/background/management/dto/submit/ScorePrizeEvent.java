package cn.mir.background.management.dto.submit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * 客户端请求数据接收类：提交的奖扣事件
 *
 * @author Eros
 * @date 2022/1/25 17:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScorePrizeEvent {
    /**
     * 事件id
     */
    @NotNull(message = "事件ID不能为空")
    private Long eventId;
    /**
     * 事件时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @PastOrPresent
    private LocalDate eventDate;

    /**
     * 事件奖扣人列表
     */
    @NotNull(message = "请选择事件奖扣人")
    @Size(min = 1, message = "请选择事件奖扣人，至少一个")
    @Valid
    private List<ScorePrizeEventUser> users;
}
