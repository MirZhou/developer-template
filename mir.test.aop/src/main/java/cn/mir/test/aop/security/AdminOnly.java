package cn.mir.test.aop.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解：仅允许Admin权限
 * <p>Create time: 2019/4/9 10:22 PM</p>
 *
 * @author 周光兵
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AdminOnly {
}