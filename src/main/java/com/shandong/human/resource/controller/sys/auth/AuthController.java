package com.shandong.human.resource.controller.sys.auth;

import com.shandong.human.resource.common.AuthTree;
import com.shandong.human.resource.domain.Auth;
import com.shandong.human.resource.service.sys.AuthService;
import com.shandong.human.resource.util.Constant;
import com.shandong.human.resource.util.RegExpUtil;
import com.shandong.human.resource.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Brief: 权限管理类
 * Author: syc
 * Date: 2016/3/15
 */
@RequestMapping("/sys/auth")
@Controller
public class AuthController {

    // 静态资源前缀
    public static final String STATIC_PREFIX = "human-resource/sys/auth";

    /**
     * 权限名正则约束
     **/
    private static final String NAME_RESTRICT = "(" + RegExpUtil.zh_CN+"|"+ RegExpUtil.NUM_CHAR+"){1,10}";

    //正则表达式需要替换的字符 '$', '(', ')', '*', '+', '.', '[', ']', '?', '\\', '^', '{', '}', '|'
    /**
     * 权限链接正则约束
     **/
    private static final String URL_RESTRICT = "[/]([/|;|:|<|>|%]|" + RegExpUtil.zh_CN + "|" + RegExpUtil.NUM_CHAR + "|" + RegExpUtil.SPECIFIC + ")*";

    @Autowired
    private AuthService service;

    /**
     * 获取权限树并转到添加权限页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAddPage(String toPageNum, HttpServletRequest request, HttpServletResponse response) {
        int pageNum = 1;
        try {
            pageNum = Integer.parseInt(toPageNum);
        } catch (Exception e) {
        }


        List<Auth> allAuth = service.selectAll();
        AuthTree existAuth = new AuthTree(0, allAuth);
        request.setAttribute("existAuth", existAuth);
        return STATIC_PREFIX + "/add";
    }

    /**
     * 对提交的添加权限信息进行处理
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public
    @ResponseBody
    Result addAuth(Integer pid, String name, String url,
                   HttpServletRequest request, HttpServletResponse response) throws IOException {
        Auth toInsert = new Auth();

        Pattern pattern = Pattern.compile(NAME_RESTRICT);
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            return new Result(Result.Status.ERROR, Constant.AUTHNAME_ILLEGAL);
        }

        Pattern pattern_2 = Pattern.compile(URL_RESTRICT);
        Matcher matcher_2 = pattern_2.matcher(url);
        if (!matcher_2.matches()) {
            return new Result(Result.Status.ERROR, Constant.AUTHURL_ILLEGAL);
        }

        int level = service.selectByID(pid).getLevel() + 1;
        toInsert.setId(0);
        toInsert.setLevel(level);
        toInsert.setName(name);
        toInsert.setUrl(url);
        toInsert.setPid(pid);

        service.insertAuth(toInsert);

        return new Result(Result.Status.SUCCESS, Constant.DEAL_SUCCESS);
    }

    /**
     * 转到删除权限页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String toDeletePage(HttpServletRequest request, HttpServletResponse response) {
        List<Auth> allAuth = service.selectAll();
        AuthTree existAuth = new AuthTree(0, allAuth);
        request.setAttribute("existAuth", existAuth);
        return STATIC_PREFIX + "/delete";
    }

    /**
     * 根据ID删除指定权限及其子权限
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    Result deleteAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id_s = request.getParameter("id");
        int id = Integer.parseInt(id_s);
        Auth root = service.selectByID(id);
        if (root == null || root.getLevel() == 0) {
            return new Result(Result.Status.ERROR, Constant.AUTHDELETE_REFUSE);
        }

        List<Auth> allAuth = service.selectAll();
        AuthTree authTree = new AuthTree(root.getLevel(), allAuth);

        /*将树指针定位到目标节点*/
        Auth target;
        for (target = authTree.now();
             target != null && target.getId() != root.getId();
             authTree.gotoNext(), target = authTree.now())
            ;

        if (target == null) {
            return new Result(Result.Status.ERROR, Constant.AUTHDELETE_REFUSE);
        }

        /*删除其子节点*/
        AuthTree childs = authTree.childAuths();
        deleteAuthTree(childs);

        /*删除该节点*/
        service.deleteByID(target.getId());
        return new Result(Result.Status.SUCCESS, Constant.DEAL_SUCCESS);
    }

    /**
     * 从数据库中删除传入的权限树所包含的该权限树等级的权限及其子权限
     *
     * @param tree 传入权限树
     */
    private void deleteAuthTree(AuthTree tree) {
        if (tree == null)
            return;
        Auth str = tree.now();
        while (str != null) {
            deleteAuthTree(tree.childAuths());
            service.deleteByID(str.getId());
            tree.gotoNext();
            str = tree.now();
        }
    }
}