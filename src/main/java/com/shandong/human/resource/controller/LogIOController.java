package com.shandong.human.resource.controller;

import com.shandong.human.resource.domain.*;
import com.shandong.human.resource.service.sys.AuthRoleService;
import com.shandong.human.resource.service.sys.AuthService;
import com.shandong.human.resource.service.sys.UserRoleService;
import com.shandong.human.resource.service.sys.UserService;
import com.shandong.human.resource.util.Constant;
import com.shandong.human.resource.util.MD5;
import com.shandong.human.resource.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 登入登出控制器
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/25/16 下午2:17
 */
@Controller
public class LogIOController {

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    AuthRoleService authRoleService;

    @Autowired
    AuthService authService;

    // 静态资源前缀
    public static final String STATIC_PREFIX = "human-resource";

    /**
     * 转登录界面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLoginPage(Model model) {
        return STATIC_PREFIX + "/login";
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public
    @ResponseBody
    Result login(String username, String password, HttpSession session) {
        username = username.trim();
        password = password.trim();
        if (username.equals("") || password.equals("")) {
            return new Result(Result.Status.ERROR, Constant.USERNAME_PASSWORD_CANNOT_BE_EMPTY);
        }
        // 用户密码MD5加盐加密
        String encStr = password + username + Constant.MD5_HASH;
        encStr = MD5.digest(encStr);
        User localUser = userService.selectByNamePwd(username, encStr);
        if (localUser == null) {
            return new Result(Result.Status.ERROR, Constant.USERNAME_PASSWORD_ERROR);
        }

        // 查找用户所有的权限
        List<UserRole> roles = userRoleService.getRoleByUserID(localUser.getId());
        Set<Auth> auth = new HashSet<Auth>();
        for (UserRole r : roles) {
            List<AuthRole> roleAuth = authRoleService.selectByRoleID(r.getRole_id());
            for (AuthRole c : roleAuth) {
                Auth oneAuth = authService.selectByID(c.getAuth_id());
                if (oneAuth != null) {
                    auth.add(oneAuth);
                }
            }
        }
        if (auth.isEmpty())
            auth = null;

        session.setAttribute(Constant.LOGIN_USER, localUser);
        session.setAttribute("auth", auth);
        return new Result(Result.Status.SUCCESS, Constant.LOGIN_SUCCESS);
    }

    /**
     * 登出
     *
     * @param model model
     * @return 视图
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(Model model, HttpSession session,
                       HttpServletRequest request, HttpServletResponse response) {
        session.setAttribute(Constant.LOGIN_USER, null);
        session.setAttribute("auth", null);
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
