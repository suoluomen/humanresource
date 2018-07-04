package com.shandong.human.resource.service.sys.impl;

import com.shandong.human.resource.domain.Auth;
import com.shandong.human.resource.mapper.sys.AuthMapper;
import com.shandong.human.resource.service.sys.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限Service实现类
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/15/16 下午2:23
 */
@Service
@Transactional(isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper service;

    /**
     * 插入权限
     *
     * @param auth
     * @return
     */
    public int insertAuth(Auth auth) {
        return service.insertAuth(auth);
    }

    /**
     * 获得所有的权限
     *
     * @return
     */
    public List<Auth> selectAll() {
        return service.selectAll();
    }

    /**
     * 通过id查询权限
     *
     * @param id
     * @return
     */
    public Auth selectByID(int id) {
        return service.selectByID(id);
    }

    /**
     * 通过id删除权限
     *
     * @param id
     * @return
     */
    public int deleteByID(int id) {
        return service.deleteByID(id);
    }
}
