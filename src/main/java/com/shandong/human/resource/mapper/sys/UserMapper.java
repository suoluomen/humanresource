package com.shandong.human.resource.mapper.sys;

import com.shandong.human.resource.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户Mapper
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/19/16 下午1:21
 */
public interface UserMapper {

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
     * @param pos pos.limit：起始位置 pos.size查询个数
     * @return
     */
    List<User> selectByPos(Map<Object, Object> pos);

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
     * @param user
     * @return
     */
    User selectByNamePwd(Map<Object, Object> user);

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
