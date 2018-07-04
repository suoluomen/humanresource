package com.shandong.human.resource.service.sys;

import com.shandong.human.resource.domain.Auth;

import java.util.List;

/**
 * 权限Service
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/15/16 下午2:23
 */
public interface AuthService {

    /**
     * 插入权限
     *
     * @param auth
     * @return
     */
    int insertAuth(Auth auth);

    /**
     * 获得所有的权限
     *
     * @return
     */
    List<Auth> selectAll();

    /**
     * 通过id查询权限
     *
     * @param id
     * @return
     */
    Auth selectByID(int id);

    /**
     * 通过id删除权限
     *
     * @param id
     * @return
     */
    int deleteByID(int id);
}
