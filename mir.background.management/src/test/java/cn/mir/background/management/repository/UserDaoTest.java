package cn.mir.background.management.repository;

import cn.mir.background.management.dto.submit.DepartmentUserDto;
import cn.mir.background.management.entity.Department;
import cn.mir.background.management.entity.User;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 测试类：用户Dao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    @Autowired
    private EntityManager entityManager;

    /**
     * 测试方法：插入管理员用户
     */
    @Test
    public void testInsertAdminUser() {
        User entity = new User();
        entity.setCode("administrator");
        entity.setUsername("administrator");
        entity.setRealName("超级管理员");
        entity.setPassword("123456");
        entity.setLoginCount(0);
        entity.setIsStaff(1);
        entity.setMarkEnable(1);
        entity.setUserSource(1);
        entity.setDescription("超级管理员");
        entity.setMarkDelete(0);
        entity.setCreateTime(LocalDateTime.now());

        // 执行保存
        this.userDao.saveAndFlush(entity);

        this.logger.info("保存用户-->{}", entity);
    }

    @Test
    @Transactional
    public void testMultiTableJoin() {
        List<DepartmentUserDto> list = this.entityManager
                .createQuery(
                        "select " +
                                "new cn.mir.background.management.dto.submit.DepartmentUserDto(user.code, user.realName, dept.name) " +
                                "from User user, Department dept " +
                                "where user.departmentId = dept.id", DepartmentUserDto.class)
                .getResultList();

        list.forEach(item -> {
            System.out.printf("code:%s \t realName:%s \t departmentName:%s \r\n", item.getCode(), item.getRealName(), item.getDepartmentName());
        });

        List<Object[]> list2 = this.entityManager
                .createQuery(
                        "select user.code, user.realName, dept.name from User user, Department dept " +
                                "where user.departmentId = dept.id", Object[].class)
                .getResultList();
        list2.forEach(obj -> {
            System.out.printf("code:%s \t realName:%s \t departmentName:%s \r\n", obj[0], obj[1], obj[2]);
        });

        List<Object[]> list3 = this.entityManager
                .createQuery(
                        "select user, dept from User user, Department dept " +
                                "where user.departmentId = dept.id", Object[].class)
                .getResultList();
        list3.forEach(obj -> {
            User user = (User) obj[0];
            Department department = (Department) obj[1];

            System.out.printf("code:%s \t realName:%s \t departmentName:%s \r\n", user.getCode(), user.getRealName(), department.getName());
        });

        System.out.println("===========================================");

        Session session = this.entityManager.unwrap(Session.class);

      List<DepartmentUserDto> list4=  session.createQuery("select user.code, dept.name AS departmentName, user.realName " +
                "from User user, Department dept " +
                "where user.departmentId = dept.id")
                .setResultTransformer(Transformers.aliasToBean(DepartmentUserDto.class))
                .getResultList();

        list4.forEach(item -> {
            System.out.printf("code:%s \t realName:%s \t departmentName:%s \r\n", item.getCode(), item.getRealName(), item.getDepartmentName());
        });

        List<DepartmentUserDto> list5 = session.createNativeQuery(
                "select user.code, dept.name AS departmentName, user.real_name AS realName " +
                        "from base_user user, base_department dept " +
                        "where user.department_id = dept.id")
                .setResultTransformer(Transformers.aliasToBean(DepartmentUserDto.class))
                .getResultList();

        list5.forEach(item -> {
            System.out.printf("code:%s \t realName:%s \t departmentName:%s \r\n", item.getCode(), item.getRealName(), item.getDepartmentName());
        });

    }
}