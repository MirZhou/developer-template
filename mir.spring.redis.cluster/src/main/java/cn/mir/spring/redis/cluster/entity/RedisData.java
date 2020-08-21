package cn.mir.spring.redis.cluster.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>create time：2020-08-21 09:33
 *
 * @author Eros
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisData {
    /**
     * ID
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
}
