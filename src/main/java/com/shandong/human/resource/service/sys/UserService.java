package com.shandong.human.resource.service.sys;

import com.shandong.human.resource.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户Service
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/16/16 下午3:25
 */
public interface UserService {

    /**
     * 获取用户数
     *
     * @return
     */
    int getCount();

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> selectAll();

    /**
     * 按位置查询
     *
     * @param offset 起始位置
     * @param size   查询个数
     * @return
     */
    List<User> selectByPos(Integer offset, Integer size);

    /**
     * 插入用户
     *
     * @param user 待插入用户
     * @return
     */
    int insertUser(User user);

    /**
     * 删除用户
     *
     * @param id 待删除用户ID
     * @return
     */
    int deleteByID(Integer id);

    /**
     * 按用户名及密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    User selectByNamePwd(String username, String password);

    /**
     * 按id查询用户
     *
     * @param id
     * @return
     */
    User selectByID(Integer id);

    /**
     * 按用户名查询用户
     *
     * @param username
     * @return
     */
    User selectByName(String username);

    /**
     * 按用户名查询用户
     *
     * @param type
     * @return
     */
    List<User> selectByType(Integer type);
}
