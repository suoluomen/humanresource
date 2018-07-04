package com.shandong.human.resource.controller.sys.user;

import com.shandong.human.resource.controller.CommonController;
import com.shandong.human.resource.domain.Role;
import com.shandong.human.resource.domain.UserRole;
import com.shandong.human.resource.service.sys.AuthRoleService;
import com.shandong.human.resource.service.sys.AuthService;
import com.shandong.human.resource.service.sys.RoleService;
import com.shandong.human.resource.service.sys.UserRoleService;
import com.shandong.human.resource.util.Constant;
import com.shandong.human.resource.util.Result;
import org.apache.ibatis.annotations.Param;
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
 * 用户角色控制器
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/16/16 下午2:15
 * <p>
 * Modified by noprom on 3/20/16.
 */
@RequestMapping("/sys")
@Controller
public class UserRoleController extends CommonController {

    // 静态资源前缀
    public static final String STATIC_PREFIX = "human-resource/sys/userRole";

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 转到用户角色设置页面
     *
     * @param uid      用户id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/userRole/edit/{id}", method = RequestMethod.GET)
    public String toEditPage(@PathVariable("id") Integer uid, Model model,
                             HttpServletRequest request, HttpServletResponse response) {
        if (uid == null)
            return "/404";

        List<Role> allRole = roleService.selectAll();
        List<UserRole> hasRole = userRoleService.getRoleByUserID(uid);
        for (Role role : allRole) {
            logger.info(role.getName());
            if (roleIdInUserRole(role.getId(), hasRole)) {
                role.setHasRole(true);
            } else {
                role.setHasRole(false);
            }
        }
        model.addAttribute("hasRole", hasRole);
        model.addAttribute("allRole", allRole);
        model.addAttribute("user_id", uid);
        return STATIC_PREFIX + "/edit";
    }

    /**
     * 用户角色设置
     *
     * @param roles    新的角色id
     * @param user_id  用户id
     * @param request
     * @param response
     */
    @RequestMapping(value = "/userRole/edit", method = RequestMethod.POST)
    public
    @ResponseBody
    Result submitHandle(String[] roles, String user_id) {
        if (user_id == null) {
            logger.debug("invalid user_id");
        }
        Integer user_id_i = Integer.parseInt(user_id);
        userRoleService.delectByUserID(user_id_i);
        boolean success = true;
        for (int i = 0; i < roles.length; ++i) {
            if (roles[i] != null) {
                Integer role_id = Integer.parseInt(roles[i]);
                int status = userRoleService.insertUserRole(user_id_i, role_id);
                if (status <= 0) {
                    success = false;
                }
            }
        }
        if (success) {
            return new Result(Result.Status.SUCCESS, Constant.DEAL_SUCCESS);
        } else {
            return new Result(Result.Status.ERROR, Constant.DEAL_FAIL);
        }
    }

    /**
     * 判断一个roleId是否在一个角色列表中
     *
     * @param roleId
     * @param hasRole
     * @return
     */
    private Boolean roleIdInUserRole(Integer roleId, List<UserRole> hasRole) {
        for (UserRole uRole : hasRole) {
            if (uRole.getRole_id().equals(roleId)) {
                return true;
            }
        }
        return false;
    }
}