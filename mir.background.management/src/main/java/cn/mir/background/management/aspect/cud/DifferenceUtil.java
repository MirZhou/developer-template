package cn.mir.background.management.aspect.cud;

import cn.mir.background.management.aspect.cud.domain.ChangeItem;
import com.alibaba.fastjson.JSON;
import javassist.NotFoundException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import javax.persistence.Id;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

/**
 * 通用类：差异
 * <p>Create time: 2019/4/13 3:16 PM</p>
 *
 * @author 周光兵
 */
public class DifferenceUtil {
    public static List<ChangeItem> getInsertChangeItems(Object object) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 创建返回对象
        List<ChangeItem> changeItems = new ArrayList<>();

        // 将Java bean对象转换为Map对象
        Map<String, String> valueMap = BeanUtils.describe(object);
        // 获取有@DataLog标注的字段与注解信息对象
        Map<String, String> fieldDataLogNameMap = getFieldDataLogNameMap(object.getClass());

        // 遍历Map对象
        for (Map.Entry<String, String> keyValue : valueMap.entrySet()) {
            // 字段名
            String field = keyValue.getKey();
            // 字段值
            String value = keyValue.getValue();

            // 创建对象并赋值
            ChangeItem changeItem = new ChangeItem();
            changeItem.setField(field);
            changeItem.setFieldName(Optional.ofNullable(fieldDataLogNameMap.get(field)).orElse(field));
            changeItem.setOldValue("");
            changeItem.setNewValue(value);

            // 添加到返回对象中
            changeItems.add(changeItem);
        }

        // 返回对象
        return changeItems;
    }

    public static ChangeItem getDeleteChangeItem(Object object) {
        ChangeItem changeItem = new ChangeItem();
        changeItem.setOldValue(JSON.toJSONString(object));
        changeItem.setNewValue("");

        return changeItem;
    }

    public static List<ChangeItem> getChangeItems(Object oldObject, Object newObject) {
        Class cl = oldObject.getClass();

        // 存放修改的字段信息
        List<ChangeItem> changeItems = new ArrayList<>();

        // 获取有@DataLog标注的字段与注解信息对象
        Map<String, String> fieldDataLogNameMap = getFieldDataLogNameMap(cl);

        try {
            // 获取Java bean的信息
            BeanInfo beanInfo = Introspector.getBeanInfo(cl, Object.class);

            // 遍历Java bean的字段属性
            for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                // 获取字段名称
                String field = propertyDescriptor.getName();

                // 获取字段旧值
                String oldValue = getValue(PropertyUtils.getProperty(oldObject, field));
                // 获取字段新值
                String newValue = getValue(PropertyUtils.getProperty(newObject, field));

                // 新旧值对比
                if (!Objects.equals(oldValue, newValue)) {
                    // 创建对象并赋值
                    ChangeItem changeItem = new ChangeItem();
                    changeItem.setField(field);
                    changeItem.setFieldName(Optional.ofNullable(fieldDataLogNameMap.get(field)).orElse(field));
                    changeItem.setOldValue(oldValue);
                    changeItem.setNewValue(newValue);

                    // 添加到List对象中
                    changeItems.add(changeItem);
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 返回对象
        return changeItems;
    }

    private static String getValue(Object propertyValue) {
        // NULL值处理
        if (propertyValue == null) {
            return "";
        }

        // LocalDate类型值处理
        if (propertyValue instanceof LocalDate) {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((TemporalAccessor) propertyValue);
        }

        // LocalDateTime类型值处理
        if (propertyValue instanceof LocalDateTime) {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format((TemporalAccessor) propertyValue);

        }

        // 转换为字符串
        return propertyValue.toString();
    }

    /**
     * 返回有@DataLog标注的字段与注解信息对象
     *
     * @param clz 要查询的类
     * @return Key/Value对象
     */
    private static Map<String, String> getFieldDataLogNameMap(Class<?> clz) {
        // 要返回的Key/Value对象
        Map<String, String> map = new HashMap<>(12);

        // 遍历类对象的所有属性
        for (Field field : clz.getDeclaredFields()) {

            // 找到有@DataLog(cn.mir.background.management.aspect.cud.DataLog)标注的字段
            if (field.isAnnotationPresent(DataLog.class)) {
                // 获取注解信息
                DataLog dataLog = field.getAnnotation(DataLog.class);

                // 将字段名与注解信息写入对象中
                map.put(field.getName(), dataLog.name());
            }
        }

        return map;
    }

    /**
     * 通过ID值获取对象
     *
     * @param target 操作的目标类
     * @param id     ID值
     * @return 查询到的对象
     * @throws NoSuchMethodException     异常：未找到指定的方法
     * @throws InvocationTargetException 异常：调用目标方法异常
     * @throws IllegalAccessException    异常：非法访问
     */
    public static Object getObjectById(Object target, Object id) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NotFoundException {
        Method findMethod = target.getClass().getDeclaredMethod("findById", Object.class);
        Optional optional = (Optional) findMethod.invoke(target, id);

        if (!optional.isPresent()) {
            throw new NotFoundException("未找到指定记录");
        }

        return optional.get();
    }

    public static String getObjectFieldIdName(Object obj) {
        // 遍历类对象的所有属性
        for (Field field : obj.getClass().getDeclaredFields()) {
            // 找到有@Id(javax.persistence.Id)标注的字段
            if (field.isAnnotationPresent(Id.class)) {
                return field.getName();
            }
        }
        return "";
    }
}