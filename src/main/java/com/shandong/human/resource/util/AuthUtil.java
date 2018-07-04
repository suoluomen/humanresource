package com.shandong.human.resource.util;

import com.shandong.human.resource.domain.Auth;

import java.util.ArrayList;
import java.util.List;

/**
 * AuthUtil
 * 权限控制工具类
 * <p>
 * Author: Noprom <tyee.noprom@qq.com>
 * Date: 3/20/16 1:43 PM.
 */
public class AuthUtil {

    private List<Auth> authList = new ArrayList<Auth>();

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param pid  传入的父节点ID
     * @return String
     */
    public List<Auth> getAuthList(List<Auth> list, Integer pid) {
        for (Auth auth : list) {
            if (auth.getPid().equals(pid)) {
                recursionFn(list, auth);
            }
        }
        return authList;
    }

    /**
     * 递归调用节点
     *
     * @param list
     * @param node
     */
    private void recursionFn(List<Auth> list, Auth node) {
        List<Auth> childList = getChildList(list, node);// 得到子节点列表
        node.setChildList(childList);
        authList.add(node);
        if (childList.size() > 0) {// 判断是否有子节点
            for (Auth child : childList) {
                recursionFn(list, child);
            }
        }
    }

    /**
     * 得到子节点列表
     *
     * @param list
     * @param auth
     * @return
     */
    private List<Auth> getChildList(List<Auth> list, Auth auth) {
        List<Auth> authList = new ArrayList<Auth>();
        for (Auth child : list) {
            if (child.getPid().equals(auth.getId())) {
                authList.add(child);
            }
        }
        return authList;
    }

}
