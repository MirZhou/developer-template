package cn.mir.spring.generator.enums;

/**
 * mybatis枚举通用方法封装类
 * <p>create time：2020-08-04 19:16
 *
 * @author Eros
 */
public class MybatisEnumUtil {
    /**
     * 通过枚举项的值，从指定枚举对象中读取其枚举项
     *
     * @param cls   枚举对象的class
     * @param value 枚举项的值
     * @param <E>   枚举对象类型
     * @return 枚举项
     */
    public static <E extends Enum<?> & IMybatisEnum> E valueOf(Class<E> cls, int value) {
        E[] enumConstants = cls.getEnumConstants();

        for (E e : enumConstants) {
            if (e.getValue() == value) {
                return e;
            }
        }

        return null;
    }
}
