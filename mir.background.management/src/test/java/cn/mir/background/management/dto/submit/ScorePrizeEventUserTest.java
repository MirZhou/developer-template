package cn.mir.background.management.dto.submit;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Eros
 * @date 2022/1/25 20:57
 */
public class ScorePrizeEventUserTest {
    @Test
    public void testValidScore() {
        // aScore: 1, aScoreType: null
        assertFalse(ScorePrizeEventUser.builder()
            .scoreA(30)
            .scoreTypeA(null)
            .build()
            .getValidScore());

        // aScore: null, aScoreType: 1
        assertFalse(ScorePrizeEventUser.builder()
            .scoreA(null)
            .scoreTypeA(1)
            .build()
            .getValidScore());

        // aScore: null, aScoreType: null
        assertFalse(ScorePrizeEventUser.builder()
            .scoreA(null)
            .scoreTypeA(null)
            .build()
            .getValidScore());

        // aScore: 0, aScoreType: 1
        assertFalse(ScorePrizeEventUser.builder()
            .scoreA(0)
            .scoreTypeA(1)
            .build()
            .getValidScore());

        // aScore: 1, aScoreType: 1
        assertTrue(ScorePrizeEventUser.builder()
            .scoreA(1)
            .scoreTypeA(1)
            .build()
            .getValidScore());

        // aScore: 1, aScoreType: 0
        assertFalse(ScorePrizeEventUser.builder()
            .scoreA(1)
            .scoreTypeA(0)
            .build()
            .getValidScore());
    }
}
