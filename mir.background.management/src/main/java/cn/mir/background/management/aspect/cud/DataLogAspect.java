package cn.mir.background.management.aspect.cud;

import cn.mir.background.management.aspect.cud.domain.Action;
import cn.mir.background.management.aspect.cud.domain.ChangeItem;
import cn.mir.background.management.aspect.cud.repository.ActionDao;
import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * AOP拦截器：数据CUD日志
 * <p>Create time: 2019/4/13 3:00 PM</p>
 *
 * @author 周光兵
 */
@Aspect
@Component
public class DataLogAspect {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(DataLogAspect.class);

    /**
     * 动作Dao
     */
    private final ActionDao actionDao;

    /**
     * 构造方法注入对象
     *
     * @param actionDao 动作Dao
     */
    @Autowired
    public DataLogAspect(ActionDao actionDao) {
        this.actionDao = actionDao;
    }

    /**
     * 拦截Dao接口的save方法
     */
    @Pointcut("execution(public * cn.mir.background.management.repository.*.save*(..))")
    public void save() {
    }

    /**
     * 拦截Dao接口的delete方法
     */
    @Pointcut("execution(public * cn.mir.background.management.repository.*.delete*(..))")
    public void delete() {

    }

    /**
     * 拦截save()或者delete()后的具体操作
     *
     * @param proceedingJoinPoint 连接点对象
     * @return 稍候完善
     */
    @Around("save() || delete()")
    public Object cudOperationLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 用于存放执行完毕之后的返回对象
        Object returnObj = null;

        // save方法名的前缀
        String methodNameSaveStartsWith = "save";
        // 获取拦截到的方法名
        String methodName = proceedingJoinPoint.getSignature().getName();

        // 操作类型
        ActionType actionType;
        // 操作对象的主键值（actionType为UPDATE或DELETE时，存在该值）
        String id = null;
        // 旧对象（actionType为UPDATE或DELETE时，存在该值）
        Object oldObject = null;
        // 操作对象的class name
        String objectClass;
        // 存放修改的列信息
        List<ChangeItem> changeItems = new ArrayList<>();

        try {

            // 获取对象
            Object obj = proceedingJoinPoint.getArgs()[0];
            oldObject = obj.getClass().getDeclaredConstructor().newInstance();

            // 获取class name
            objectClass = obj.getClass().getName();
            // 获取操作对象主键ID的字段名
            String fieldIdName = DifferenceUtil.getObjectFieldIdName(obj);

            // 获取ID值（actionType为UPDATE或DELETE时，存在该值）
            Object objectIdValue = PropertyUtils.getProperty(obj, fieldIdName);

            // 目标方法执行前处理程序 Start

            if (methodName.startsWith(methodNameSaveStartsWith)) {
                // insert or update

                if (objectIdValue == null) {
                    // 执行插入操作
                    actionType = ActionType.INSERT;
                } else {
                    // 执行更新操作
                    actionType = ActionType.UPDATE;

                    // 获取ID值
                    id = objectIdValue.toString();

                    // 获取修改前的对象
                    BeanUtils.copyProperties(DifferenceUtil.getObjectById(proceedingJoinPoint.getTarget(), objectIdValue), oldObject);
                }

            } else {
                // delete
                actionType = ActionType.DELETE;

                if (objectIdValue == null) {
                    throw new Exception("参数读取异常");
                }

                // 获取ID值
                id = objectIdValue.toString();

                // 获取删除前的对象
                BeanUtils.copyProperties(DifferenceUtil.getObjectById(proceedingJoinPoint.getTarget(), objectIdValue), oldObject);
            }

            // 目标方法执行前处理程序 End

            // 目标方法执行完毕，并获取其返回值
            returnObj = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());

            // 目标方法执行完毕后处理程序 Start

            switch (actionType) {
                case INSERT: {
                    // 获取完成插入操作后对象的ID值
                    id = PropertyUtils.getProperty(returnObj, fieldIdName).toString();

                    // 插入操作时，操作的对象即为修改的对象
                    changeItems.addAll(DifferenceUtil.getInsertChangeItems(obj));
                }
                break;
                case UPDATE: {
                    // 获取完成更新后的对象
                    Object newObject = DifferenceUtil.getObjectById(proceedingJoinPoint.getTarget(), objectIdValue);
                    // 对比获取更新前后更改的字段信息
                    changeItems.addAll(DifferenceUtil.getChangeItems(oldObject, newObject));
                }
                break;
                case DELETE: {
                    changeItems.add(DifferenceUtil.getDeleteChangeItem(oldObject));
                }
                break;
                default:
                    break;
            }

            // 目标方法执行完毕后处理程序 End

            // 创建对象并赋值
            Action action = new Action();
            action.setObjectId(id);
            action.setObjectClass(objectClass);
            action.setOperator("测试");
            action.setOperateTime(LocalDateTime.now());
            action.setActionType(actionType);
            action.getChanges().addAll(changeItems);

            // 将对象写入DB中
            this.actionDao.save(action);

        } catch (Exception e) {
            // 记录错误日志
            logger.error(e.getMessage(), e);
        }

        return returnObj;
    }
}