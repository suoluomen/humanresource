package com.shandong.human.resource.service.sys;

import com.shandong.human.resource.domain.AuthRole;
import com.shandong.human.resource.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 权限角色service
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/16/16 下午3:43
 */
public interface AuthRoleService {

    /**
     * 插入角色权限
     *
     * @param auth_id 角色id
     * @param role_id 权限id
     * @return
     */
    int insertAuthRole(int auth_id, int role_id);

    /**
     * 删除角色权限
     *
     * @param auth_id 角色id
     * @param role_id 权限id
     * @return
     */
    int deleteAuthRole(int auth_id, int role_id);

    /**
     * 根据id查询角色所有权限
     *
     * @param role_id 待查询角色id
     * @return
     */
    List<AuthRole> selectByRoleID(int role_id);

    /**
     * 删除角色的所有权限
     *
     * @param role_id 待删除角色id
     * @return
     */
    int deleteByRoleID(int role_id);
}
