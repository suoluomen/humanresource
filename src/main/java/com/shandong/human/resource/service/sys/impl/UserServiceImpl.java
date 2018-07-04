package com.shandong.human.resource.service.sys.impl;

import com.shandong.human.resource.domain.User;
import com.shandong.human.resource.mapper.sys.UserMapper;
import com.shandong.human.resource.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户Service实现类
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/16/16 下午3:25
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper service;

    /**
     * 获取用户数
     *
     * @return
     */
    public int getCount() {
        return service.getCount();
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    public List<User> selectAll() {
        return service.selectAll();
    }

    /**
     * 按位置查询
     *
     * @param offset 起始位置
     * @param size   查询个数
     * @return
     */
    public List<User> selectByPos(Integer offset, Integer size) {
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("offset", offset);
        info.put("size", size);
        return service.selectByPos(info);
    }

    /**
     * 插入用户
     *
     * @param user 待插入用户
     * @return
     */
    public int insertUser(User user) {
        return service.insertUser(user);
    }

    /**
     * 删除用户
     *
     * @param id 待删除用户ID
     * @return
     */
    public int deleteByID(Integer id) {
        return service.deleteByID(id);
    }

    /**
     * 按用户名及密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    public User selectByNamePwd(String username, String password) {
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("username", username);
        info.put("password", password);
        return service.selectByNamePwd(info);
    }

    /**
     * 按id查询用户
     *
     * @param id
     * @return
     */
    public User selectByID(Integer id){
        return  service.selectByID(id);
    }


    /**
     * 按用户名查询用户
     *
     * @param username
     * @return
     */
    public User selectByName(String username){
        return service.selectByName(username);
    }


    /**
     * 按用户名查询用户
     *
     * @param type
     * @return
     */
    public List<User> selectByType(Integer type){
        return service.selectByType(type);
    }
}
