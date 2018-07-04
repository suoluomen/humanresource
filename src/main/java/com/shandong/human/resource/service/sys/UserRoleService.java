package com.shandong.human.resource.service.sys;

import com.shandong.human.resource.domain.UserRole;

import java.util.List;
import java.util.Map;

/**
 * 用户角色关联service
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/17/16 下午1:53
 */
public interface UserRoleService {

    /**
     * 获取所有用户角色信息
     *
     * @return
     */
    List<UserRole> getAll();

    /**
     * 获取指定用户的角色信息
     *
     * @param id 用户id
     * @return
     */
    List<UserRole> getRoleByUserID(Integer id);

    /**
     * 插入一个用户角色
     *
     * @param user_id 用户id
     * @param role_id 角色id
     * @return
     */
    int insertUserRole(int user_id, int role_id);

    /**
     * 删除一个用户角色
     *
     * @param user_id 用户id
     * @param role_id 角色id
     * @return
     */
    int delectUserRole(int user_id, int role_id);

    /**
     * 删除一个用户的所有角色
     *
     * @param user_id
     * @return
     */
    int delectByUserID(Integer user_id);
}
