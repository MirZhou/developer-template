package cn.mir.background.management.dto.submit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * 客户端请求数据接收类：提交奖扣
 *
 * @author Eros
 * @date 2022/1/25 17:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScorePrize {
    @NotBlank(message = "奖扣主题不能为空")
    private String prizeName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "请选择奖扣时间")
    @PastOrPresent(message = "奖扣时间无效，拒绝穿越")
    private LocalDate prizeDate;

    @NotNull(message = "请选择奖扣事件")
    @Size(min = 1, message = "请选择奖扣事件，至少一个")
    @Valid
    private List<ScorePrizeEvent> events;

    @JsonIgnore
    @AssertTrue(message = "奖扣时间无效，惠管理还没出生")
    public boolean getValidPrizeDate() {
        if (Objects.isNull(this.prizeDate)) {
            return true;
        }

        return LocalDate.of(2019, 1, 1).isBefore(this.prizeDate);
    }
}
