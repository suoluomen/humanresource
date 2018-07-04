package com.shandong.human.resource.controller.sys.role;

import com.shandong.human.resource.domain.Role;
import com.shandong.human.resource.service.sys.RoleService;
import com.shandong.human.resource.util.Constant;
import com.shandong.human.resource.util.Pager;
import com.shandong.human.resource.util.RegExpUtil;
import com.shandong.human.resource.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class RoleController {

    // 静态资源前缀
    public static final String STATIC_PREFIX = "human-resource/sys/role";

    /**
     * 角色名正则约束
     **/
    private static final String NAME_RESTRICT = "(" + RegExpUtil.zh_CN + "|" + RegExpUtil.NUM_CHAR + "){1,10}";

    @Autowired
    private RoleService service;

    /**
     * 添加角色
     *
     * @param name
     * @param request
     * @param response
     */
    @RequestMapping(value = "/role/add", method = RequestMethod.POST)
    public
    @ResponseBody
    Result add(String name, HttpServletRequest request, HttpServletResponse response) {
        if (name == null) {
            return new Result(Result.Status.ERROR, Constant.ROLENAME_ILLEGAL);
        }

        Pattern pattern = Pattern.compile(NAME_RESTRICT);
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            return new Result(Result.Status.ERROR, Constant.ROLENAME_ILLEGAL);
        }

        service.insertRole(name);
        return new Result(Result.Status.SUCCESS, Constant.DEAL_SUCCESS);
    }

    /**
     * 删除角色
     *
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping(value = "/role/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    Result delete(Integer id, HttpServletRequest request, HttpServletResponse response) {
        if (id == null) {
            return new Result(Result.Status.ERROR, Constant.ROLEDELETE_REFUSE);
        }

        int status = service.deleteRole(id);
        if (status > 0) {
            return new Result(Result.Status.SUCCESS, Constant.DEAL_SUCCESS);
        } else {
            return new Result(Result.Status.ERROR, Constant.ROLEDELETE_REFUSE);
        }
    }

    /**
     * 显示角色
     *
     * @param pageNum
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public String toRolePage(String pageNum,
                             HttpServletRequest request, HttpServletResponse response) {
        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(pageNum);
        } catch (Exception e) {
        }

        /*单页显示个数*/
        int pageSize = 10;
        Pager<Role> pager = new Pager<Role>(pageSize);
        pager.setCount(service.getCount());
        pager.setCurrentPage(currentPage);
        int offset = pager.getMaxSize() * (currentPage - 1);
        int size = pager.getMaxSize();
        pager.setData(service.selectByPos(offset, size));
        request.setAttribute("pager", pager);
        return STATIC_PREFIX + "/list";
    }
}