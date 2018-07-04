package com.shandong.human.resource.service.sys.impl;

import com.shandong.human.resource.domain.UserRole;
import com.shandong.human.resource.mapper.sys.UserRoleMapper;
import com.shandong.human.resource.service.sys.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户角色关联service实现类
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/17/16 下午1:53
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleMapper service;

    /**
     * 获取所有用户角色信息
     *
     * @return
     */
    public List<UserRole> getAll() {
        return service.getAll();
    }

    /**
     * 获取指定用户的角色信息
     *
     * @param id 用户id
     * @return
     */
    public List<UserRole> getRoleByUserID(Integer id) {
        return service.getRoleByUserID(id);
    }

    /**
     * 插入一个用户角色
     *
     * @param user_id 用户id
     * @param role_id 角色id
     * @return
     */
    public int insertUserRole(int user_id, int role_id) {
        Map<Object, Object> toInsert = new HashMap<Object, Object>();
        toInsert.put("user_id", user_id);
        toInsert.put("role_id", role_id);
        return service.insertUserRole(toInsert);
    }

    /**
     * 删除一个用户角色
     *
     * @param user_id 用户id
     * @param role_id 角色id
     * @return
     */
    public int delectUserRole(int user_id, int role_id) {
        Map<Object, Object> toDelete = new HashMap<Object, Object>();
        toDelete.put("user_id", user_id);
        toDelete.put("role_id", role_id);
        return service.delectUserRole(toDelete);
    }

    /**
     * 删除一个用户的所有角色
     *
     * @param user_id
     * @return
     */
    public int delectByUserID(Integer user_id) {
        return service.delectByUserID(user_id);
    }
}
