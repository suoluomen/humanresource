package com.shandong.human.resource.service.sys.impl;

import com.shandong.human.resource.domain.AuthRole;
import com.shandong.human.resource.mapper.sys.AuthRoleMapper;
import com.shandong.human.resource.service.sys.AuthRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限角色service实现类
 * <p>
 * Date: 3/16/16 下午3:52
 */
@Service
public class AuthRoleServiceImpl implements AuthRoleService {

    @Autowired
    private AuthRoleMapper service;

    /**
     * 插入角色权限
     *
     * @param auth_id 角色id
     * @param role_id 权限id
     * @return
     */
    public int insertAuthRole(int auth_id, int role_id) {
        Map<Object, Object> toInsert = new HashMap<Object, Object>();
        toInsert.put("auth_id", auth_id);
        toInsert.put("role_id", role_id);
        return service.insertAuthRole(toInsert);
    }

    /**
     * 删除角色权限
     *
     * @param auth_id 角色id
     * @param role_id 权限id
     * @return
     */
    public int deleteAuthRole(int auth_id, int role_id) {
        Map<Object, Object> toDelete = new HashMap<Object, Object>();
        toDelete.put("auth_id", auth_id);
        toDelete.put("role_id", role_id);
        return service.deleteAuthRole(toDelete);
    }

    /**
     * 根据id查询角色所有权限
     *
     * @param role_id 待查询角色id
     * @return
     */
    public List<AuthRole> selectByRoleID(int role_id) {
        return service.selectByRoleID(role_id);
    }

    /**
     * 删除角色的所有权限
     *
     * @param role_id 待删除角色id
     * @return
     */
    public int deleteByRoleID(int role_id) {
        return service.deleteByRoleID(role_id);
    }
}
