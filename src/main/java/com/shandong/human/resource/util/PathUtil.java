package com.shandong.human.resource.util;

import com.shandong.human.resource.domain.Auth;

import java.util.List;

/**
 * PathUtil
 * 输出完整路径的工具类
 * <p>
 * Author: Noprom <tyee.noprom@qq.com>
 * Date: 3/29/16 2:45 PM.
 */
public class PathUtil {

    private List<Auth> path;

    public List<Auth> getPath() {
        return path;
    }

    public List<Auth> getPath(List<Auth> allAuth, String curURL) {
        for (Auth auth : allAuth) {
            if (curURL.contains(auth.getUrl())) {

            }
        }
        return path;
    }

}
