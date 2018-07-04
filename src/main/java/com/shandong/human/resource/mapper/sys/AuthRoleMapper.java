package com.shandong.human.resource.mapper.sys;

import com.shandong.human.resource.domain.AuthRole;

import java.util.List;
import java.util.Map;

/**
 * 权限角色对应关系mapper
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/16/16 下午2:17
 */
public interface AuthRoleMapper {

    /**
     * 插入角色权限
     *
     * @param authRole 待插入的角色权限
     * @return
     */
    int insertAuthRole(Map<Object, Object> authRole);

    /**
     * 删除角色权限
     *
     * @param authRole 待删除的角色权限
     * @return
     */
    int deleteAuthRole(Map<Object, Object> authRole);

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
