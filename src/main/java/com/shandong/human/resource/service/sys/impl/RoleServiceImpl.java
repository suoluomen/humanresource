package com.shandong.human.resource.service.sys.impl;

import com.shandong.human.resource.domain.Role;
import com.shandong.human.resource.mapper.sys.RoleMapper;
import com.shandong.human.resource.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色Service实现类
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/17/16 下午1:17
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper service;

    /**
     * 获取所有角色
     *
     * @return
     */
    public List<Role> selectAll() {
        return service.selectAll();
    }

    /**
     * 插入一个角色
     *
     * @param name 插入角色名
     * @return
     */
    public int insertRole(String name) {
        return service.insertRole(name);
    }

    /**
     * 删除一个角色
     *
     * @param id 待删除角色ID
     * @return
     */
    public int deleteRole(Integer id) {
        return service.deleteRole(id);
    }

    /**
     * 按照id查询角色
     *
     * @param id 待查询角色id
     * @return
     */
    public int selectByID(Integer id) {
        return service.selectByID(id);
    }

    /**
     * 获得角色总数
     *
     * @return
     */
    public Integer getCount() {
        return service.getCount();
    }

    /**
     * 分页查询
     *
     * @param offset
     * @param size
     * @return
     */
    public List<Role> selectByPos(int offset, int size) {
        Map<Object, Object> pos = new HashMap<Object, Object>();
        pos.put("offset", offset);
        pos.put("size", size);
        return service.selectByPos(pos);
    }
}
