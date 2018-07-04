package com.shandong.human.resource.mapper.sys;

import com.shandong.human.resource.domain.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色Mapper
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/17/16 下午7:15
 */
public interface RoleMapper {

    /**
     * 获取所有角色
     *
     * @return
     */
    List<Role> selectAll();

    /**
     * 插入一个角色
     *
     * @param name 插入角色名
     * @return
     */
    int insertRole(String name);

    /**
     * 删除一个角色
     *
     * @param id 待删除角色ID
     * @return
     */
    int deleteRole(Integer id);

    /**
     * 按照id查询角色
     *
     * @param id 待查询角色id
     * @return
     */
    int selectByID(Integer id);

    /**
     * 获得角色总数
     *
     * @return
     */
    Integer getCount();

    /**
     * 分页查询
     *
     * @param pos
     * @return
     */
    List<Role> selectByPos(Map<Object, Object> pos);
}
