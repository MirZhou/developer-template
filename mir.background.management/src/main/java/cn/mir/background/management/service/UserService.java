package cn.mir.background.management.service;

import cn.mir.background.management.entity.User;

/**
 * UserService
 * @author Eros
 */
public interface UserService {
    /**
     * 通过用户名查找用户信息
     * @param username 用户名
     * @return 用户信息
     */
	User getByUsername(String username);
    
}