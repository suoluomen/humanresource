package com.shandong.human.resource.controller.sys.auth;

import com.shandong.human.resource.domain.Auth;
import com.shandong.human.resource.domain.AuthRole;
import com.shandong.human.resource.service.sys.AuthRoleService;
import com.shandong.human.resource.service.sys.AuthService;
import com.shandong.human.resource.util.AuthUtil;
import com.shandong.human.resource.util.Constant;
import com.shandong.human.resource.util.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 权限控制器
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/16/16 下午2:15
 * <p>
 * Modified by noprom on 3/20/16.
 */
@RequestMapping("/sys")
@Controller
public class AuthRoleController {

    protected Logger logger = Logger.getLogger(getClass());

    // 静态资源前缀
    public static final String STATIC_PREFIX = "human-resource/sys/authRole";

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthRoleService authRoleService;

    /**
     * 转到角色权限设置页面
     *
     * @param role_id  角色id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/authRole/edit/{id}", method = RequestMethod.GET)
    public String toEditPage(@PathVariable("id") Integer role_id, Model model,
                             HttpServletRequest request, HttpServletResponse response) {
        List<Auth> allAuth = authService.selectAll();
        //AuthTree authTree = new AuthTree(0, allAuth);
        //List<Auth> existAuth = AuthTree.toList(authTree);

        role_id = role_id == null ? 0 : role_id;
        List<AuthRole> existAuthRole = authRoleService.selectByRoleID(role_id);

        // 遍历所有权限设置可见性
        for (Auth auth : allAuth) {
            if (authInAuthRole(auth.getId(), existAuthRole)) {
                auth.setHasAuth(true);
            } else {
                auth.setHasAuth(false);
            }
        }
        AuthUtil au = new AuthUtil();
        List<Auth> existAuth = au.getAuthList(allAuth, 0);
        // 显示到页面
        model.addAttribute("roleId", role_id);
        model.addAttribute("existAuth", existAuth);
        model.addAttribute("existAuthRole", existAuthRole);
        return STATIC_PREFIX + "/edit";
    }

    /**
     * 角色权限设置
     *
     * @param roleId   待处理角色id
     * @param authIds  传入权限
     * @param request
     * @param response
     */
    @RequestMapping(value = "/authRole/edit", method = RequestMethod.POST)
    public @ResponseBody
    Result submitHandle(Integer roleId, String authIds, HttpServletRequest request, HttpServletResponse response) {
        String[] auths = authIds.split(",");

        authRoleService.deleteByRoleID(roleId);
        for (String r : auths) {
            if (!r.equals("0"))
                authRoleService.insertAuthRole(Integer.parseInt(r), roleId);
        }
        return new Result(Result.Status.SUCCESS,Constant.DEAL_SUCCESS);
    }

    /**
     * 判断某个权限是否在已经存在的角色列表中
     *
     * @param authId        权限id
     * @param existAuthRole 已经存在的权限角色列表
     * @return
     */
    private Boolean authInAuthRole(Integer authId, List<AuthRole> existAuthRole) {
        for (AuthRole authRole : existAuthRole) {
            if (authRole.getAuth_id().equals(authId)) {
                return true;
            }
        }
        return false;
    }
}