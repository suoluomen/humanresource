package com.shandong.human.resource.util;

import com.shandong.human.resource.domain.Auth;

import java.util.Set;

/**
 * 检查权限的工具
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/23/16 下午2:35
 * <p>
 * Modified: by noprom <tyee.noprom@qq.com>
 * Date: 4/4/16 10:43 AM
 */
public class PermissionUtil {

    /**
     * 查询用户是否拥有对应url权限
     *
     * @param url   待查询权限
     * @param auths 用户拥有的权限
     * @return
     */
    public static boolean find(String url, Set<Auth> auths) {
        if (auths == null)
            return false;

        for (Auth r : auths) {
            if (r.getUrl().equals(url)) {
                return true;
            }
        }
        return false;
    }
}
