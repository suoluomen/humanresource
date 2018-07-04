package com.shandong.human.resource.mapper.sys;

import com.shandong.human.resource.domain.Auth;

import java.util.List;

/**
 * auth表管理dao
 * <p>
 * <p>
 * Author: syc
 * Date: 2016/3/15
 */
public interface AuthMapper {

    /**
     * 向auth表中插入一个数据
     *
     * @param auth
     * @return
     */
    int insertAuth(Auth auth);

    /**
     * 获取表中所有数据
     *
     * @return
     */
    List<Auth> selectAll();

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    Auth selectByID(int id);

    /**
     * 根据ID删除数据
     *
     * @param id
     * @return
     */
    int deleteByID(int id);
}
