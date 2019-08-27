package cn.mir.background.management.service.impl;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import cn.mir.background.management.entity.User;
import cn.mir.background.management.repository.UserDao;
import cn.mir.background.management.service.UserService;

/**
 * UserServiceImpl
 * 
 * @author Eros
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 用户Dao
     */
    private final UserDao userDao;

    /**
     * 构造方法注入对象
     * 
     * @param userDao 用户Dao
     */
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getByUsername(String username) {
        User search = new User();
        search.setUsername(username);

        return this.userDao.findOne(Example.of(search)).orElse(null);
    }

}