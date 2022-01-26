package cn.mir.background.management.dto.submit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 客户端请求数据接收类：事件下的奖扣人
 *
 * @author Eros
 * @date 2022/1/25 17:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScorePrizeEventUser {
    /**
     * 被奖扣人ID
     */
    @NotNull
    private Long userId;

    private Integer scoreA;
    private Integer scoreTypeA;

    private Integer bScore;
    private Integer bScoreType;

    private Integer opScore;
    private Integer opScoreType;

    @JsonIgnore
    @AssertTrue(message = "积分无效")
    public boolean getValidScore() {
        return this.validScoreA() || this.validScoreB() || this.validScoreOp();
    }

    private boolean validScoreA() {
        if (Objects.isNull(this.scoreA) && Objects.isNull(this.scoreTypeA)) {
            return false;
        }

        if (Objects.nonNull(this.scoreA) && Objects.nonNull(scoreTypeA)) {
            return this.scoreA != 0 && this.scoreTypeA > 0;
        }

        return false;

    }

    private boolean validScoreB() {
        if (Objects.isNull(this.bScore) && Objects.isNull(this.bScoreType)) {
            return false;
        }

        if (Objects.nonNull(this.bScore) && Objects.nonNull(bScoreType)) {
            return this.bScore != 0 && this.bScoreType > 0;
        }

        return false;
    }

    private boolean validScoreOp() {
        if (Objects.isNull(this.opScore) && Objects.isNull(this.opScoreType)) {
            return false;
        }

        if (Objects.nonNull(this.opScore) && Objects.nonNull(this.opScoreType)) {
            return this.opScore != 0 && this.opScoreType > 0;
        }

        return false;
    }
}
