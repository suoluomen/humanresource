package com.shandong.human.resource.util.interceptor;

import com.shandong.human.resource.domain.Auth;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * 登录拦截器
 *
 * @author tyee.noprom@qq.com
 * @time 2/7/16 10:16 AM.
 * <p>
 * Modify SYC<522560298@qq.com>: Rewrite
 */
public class PrivilegeInterceptor implements HandlerInterceptor {

    // 静态资源前缀
    private static final String STATIC_PREFIX = "human-resource";

    private static final String STATIC_RESOURCE = "/static";

    /**
     * 默认可访问url
     */
    private static Set<String> defaultUrl;

    /**
     * 替换符数据类型
     */
    private enum alterType {
        _BEGIN, //enum 起始
        _INTEGER, //int类型
        _END //enum 结束
    }

    /**
     * 替换符
     */
    private static Map<String, alterType> alterSign;

    static {
        defaultUrl = new HashSet<String>();
        defaultUrl.clear();
        defaultUrl.add("/static");
        defaultUrl.add("/");
        defaultUrl.add("/404");
        defaultUrl.add("/500");
        defaultUrl.add("/login");
        defaultUrl.add("/logout");
        defaultUrl.add("/error");

        alterSign = new HashMap<String, alterType>();
        alterSign.clear();
        alterSign.put("{id}", alterType._INTEGER);
    }

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String redirectUrl = doPreHandle(request, response, handler);
        if (!redirectUrl.equals(request.getRequestURI())) {
            response.sendRedirect(redirectUrl);
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, Object handler, Exception e) throws Exception {

    }


    /**
     * 字符串类型判定
     *
     * @param in     字符串
     * @param toType 目标类型
     * @return
     */
    private boolean typeCheck(String in, alterType toType) {
        switch (toType) {
            //int 判定
            case _INTEGER: {
                try {
                    Integer.parseInt(in);
                } catch (Exception e) {
                    return false;
                }
                return true;
            }
            default:
                return false;
        }
    }

    /**
     * 检查链接是否拥有助记符
     *
     * @param url 链接
     * @return
     */
    private boolean signCheck(String url) {
        for (Map.Entry<String, alterType> entry : alterSign.entrySet()) {
            if (url.contains(entry.getKey())) {
                return true;
            }
        }
        return false;
    }

    private String doPreHandle(HttpServletRequest request,
                               HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        HttpSession session = request.getSession();

        if (url == null) {
            session.setAttribute("error", "无效的链接");
            return "/error";
        }

        if (url.contains(STATIC_RESOURCE))
            return url;

        for (String r : defaultUrl) {
            if (r.contains(url)) {
                return url;
            }
        }

        if (session == null) {
            session.setAttribute("error", "您尚未登录");
            return "/error";
        }

        Set<Auth> auths = (Set<Auth>) session.getAttribute("auth");
        if (auths == null) {
            session.setAttribute("error", "您尚未登录");
            return "/error";
        }

        String[] url_splid = url.split("/");
        for (Auth r : auths) {
            //不包含替换符
            if (r.getUrl().equals(url)) {
                return url;
            }

            String r_url = r.getUrl();
            String[] r_url_splid = r_url.split("/");

            //长度不匹配
            if (r_url_splid.length != url_splid.length) {
                continue;
            }

            //包含替换符
            if (signCheck(r_url)) {
                int i;
                for (i = 0; i < r_url_splid.length; ++i) {
                    //当前节为替换符
                    if (alterSign.containsKey(r_url_splid[i])) {
                        if (typeCheck(url_splid[i], alterSign.get(r_url_splid[i]))) {
                            continue;
                        } else {
                            break;
                        }
                    }
                    //当前节非替换符
                    else {
                        if (r_url_splid[i].equals(url_splid[i])) {
                            continue;
                        } else {
                            break;
                        }
                    }
                }
                if (i == r_url_splid.length) {
                    return url;
                }
            }
        }
        session.setAttribute("error", "无效的访问请求");
        return "/error";
    }
}
