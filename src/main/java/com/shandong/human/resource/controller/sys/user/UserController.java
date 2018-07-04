package com.shandong.human.resource.controller.sys.user;

import com.shandong.human.resource.domain.Area;
import com.shandong.human.resource.domain.Company;
import com.shandong.human.resource.domain.CompanyData;
import com.shandong.human.resource.domain.User;
import com.shandong.human.resource.service.home.AreaService;
import com.shandong.human.resource.service.home.CompanyService;
import com.shandong.human.resource.service.sys.*;
import com.shandong.human.resource.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

/**
 * 用户控制器类
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/19/16 下午2:15
 */
@RequestMapping("/sys")
@Controller
public class UserController {

    // 静态资源前缀
    public static final String STATIC_PREFIX = "human-resource/sys/user";

    protected Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyDataService companyDataService;

    @Autowired
    private SurveyTimeService surveyTimeService;

    /**
     * companyData详情
     *
     * @param model
     * @param id
     * @return
     */

    @RequestMapping(value = "/user/detail/{id}", method = RequestMethod.GET)
    public String detail(Model model, @PathVariable("id") Integer id) {
        CompanyData cd = companyDataService.getCompanyDataById(id);
        model.addAttribute("check", cd);
        return STATIC_PREFIX + "/detail";
    }

    /**
     * 用户列表
     *
     * @param pageNum
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String toShowPage(String pageNum, Model model,
                             HttpServletRequest request, HttpServletResponse response) {
        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(pageNum);
        } catch (Exception e) {
        }

        /*单页显示个数*/
        int pageSize = 10;
        Pager<User> pager = new Pager<User>(pageSize);
        pager.setCount(userService.getCount());
        pager.setCurrentPage(currentPage);
        int offset = pager.getMaxSize() * (currentPage - 1);
        int size = pager.getMaxSize();
        pager.setData(userService.selectByPos(offset, size));

        List<Area> cityList = areaService.getAllCity();
        List<Area> area = new ArrayList<Area>();
        for (Area r : cityList) {
            List area_ch = areaService.getAllAreaById(r.getId());
            area.addAll(area_ch);
        }

        model.addAttribute("pager", pager);
        model.addAttribute("cityList", cityList);
        model.addAttribute("roleList", roleService.selectAll());
        model.addAttribute("areaList", area);
        model.addAttribute("surveyTimeList", surveyTimeService.getAllSurveyTime());

        return STATIC_PREFIX + "/list";
    }

    /**
     * 新增用户
     *
     * @param user
     * @param role
     */
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public
    @ResponseBody
    Result addUser(@Valid User user, BindingResult result, Integer role) {
        if (result.hasErrors()) {
            return new Result(Result.Status.ERROR, Constant.USERNAME_ILLEGAL);
        }
        // 用户密码MD5加盐加密
        String encStr = user.getPassword() + user.getUsername() + Constant.MD5_HASH;
        encStr = MD5.digest(encStr);
        user.setPassword(encStr);
        String adminPwd = "adminadmin" + Constant.MD5_HASH;
        adminPwd = MD5.digest(adminPwd);
        logger.debug("md5->" + adminPwd);
        Integer uid = userService.insertUser(user);
        if (uid > 0) {
            Integer check = userRoleService.insertUserRole(user.getId(), role);
            if (check > 0) {
                return new Result(Result.Status.SUCCESS, Constant.DEAL_SUCCESS);
            } else {
                return new Result(Result.Status.ERROR, Constant.DEAL_FAIL);
            }
        } else {
            return new Result(Result.Status.ERROR, Constant.DEAL_FAIL);
        }
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    Result deleteUser(Integer id, HttpSession session) {
        if (id.equals(1)) {
            return new Result(Result.Status.ERROR, Constant.CAN_NOT_DELETE_ADMIN);
        }

        User localUser = (User) session.getAttribute(Constant.LOGIN_USER);
        User toDelete = userService.selectByID(id);
        if(toDelete==null){
            return new Result(Result.Status.ERROR, Constant.USER_NOT_FOUND);
        }

        /*省用户只能删除市用户和企业用户*/
        if(localUser.getType()==1){
            if(toDelete.getType()<2){
                return new Result(Result.Status.ERROR, Constant.DELETE_PERMITION_ERR);
            }
        }

        /*市用户和企业用户无法删除角色*/
        else if(localUser.getType()>1){
            return new Result(Result.Status.ERROR, Constant.DELETE_PERMITION_ERR);
        }

        int status = userService.deleteByID(id);
        if (status > 0) {
            return new Result(Result.Status.SUCCESS, Constant.DEAL_SUCCESS);
        } else {
            return new Result(Result.Status.ERROR, Constant.DEAL_FAIL);
        }
    }

    /**
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/user/search", method = RequestMethod.GET)
    public String toSelectUserPage(Model model, HttpServletRequest request, HttpServletResponse response) {
        List<Area> cityList = areaService.getAllCity();
        List<Area> area = new ArrayList<Area>();
        for (Area r : cityList) {
            List area_ch = areaService.getAllAreaById(r.getId());
            area.addAll(area_ch);
        }
        model.addAttribute("cityList", cityList);
        model.addAttribute("roleList", roleService.selectAll());
        model.addAttribute("areaList", area);
        model.addAttribute("surveyTimeList", surveyTimeService.getAllSurveyTime());
        Set<User> qualifiedUser = new HashSet<User>();
        request.setAttribute("qualifiedUser", qualifiedUser);

        return STATIC_PREFIX + "/search";
    }

    /**
     * 用户条件查询
     *
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/user/search", method = RequestMethod.POST)
    public String selectUser(Model model, HttpServletRequest request, HttpServletResponse response) {

        /**
         * @param companyName  单位名称 模糊查询
         * @param userName     登陆账号
         * @param userType     用户类型
         * @param areaID       所属地市
         * @param address      所属市县 模糊查询
         * @param status       数据状态
         * @param business     单位性质 模糊查询
         * @param surveyTimeID 起始结束日期ID
         */
        String companyName = request.getParameter("companyName");
        String userName = request.getParameter("userName");
        String userType_s = request.getParameter("userType");
        Integer userType = null;
        if (userType_s != null) {
            try {
                userType = Integer.parseInt(userType_s);
            } catch (Exception e) {
                userType = null;
            }
        }
        String areaID_s = request.getParameter("areaID");
        Integer areaID = null;
        if (areaID_s != null) {
            try {
                areaID = Integer.parseInt(areaID_s);
            } catch (Exception e) {
                areaID = null;
            }
        }
        String address = request.getParameter("address");
        String status_s = request.getParameter("status");
        Integer status = null;
        if (status_s != null) {
            try {
                status = Integer.parseInt(status_s);
            } catch (Exception e) {
                status = null;
            }
        }
        String business = request.getParameter("business");
        String surveyTimeID_s = request.getParameter("surveyTimeID");
        Integer surveyTimeID = null;
        if (surveyTimeID_s != null) {
            try {
                surveyTimeID = Integer.parseInt(surveyTimeID_s);
            } catch (Exception e) {
                surveyTimeID = null;
            }
        }

        /*记录各个条件的查询结果
         * pair<用户信息,pair<公司信息，报表信息>>
         **/
        List<Set<Pair<User, Pair<Company, CompanyData>>>> data = new ArrayList<Set<Pair<User, Pair<Company, CompanyData>>>>();

        if (companyName != null && !companyName.isEmpty()) {
            List<Company> companies = companyService.fuzzySearchByName(companyName);
            Set<Pair<User, Pair<Company, CompanyData>>> data_t = new HashSet<Pair<User, Pair<Company, CompanyData>>>();
            for (Company r : companies) {
                User user = userService.selectByID(r.getId());
                if (user != null) {
                    data_t.add(new Pair<User, Pair<Company, CompanyData>>(user, new Pair<Company, CompanyData>(r, null)));
                }
            }
            if (data_t.size() != 0) {
                data.add(data_t);
            }
        }

        if (userName != null && !userName.isEmpty()) {
            User user = userService.selectByName(userName);
            Set<Pair<User, Pair<Company, CompanyData>>> data_t = new HashSet<Pair<User, Pair<Company, CompanyData>>>();
            if (user != null) {
                data_t.add(new Pair<User, Pair<Company, CompanyData>>(user, new Pair<Company, CompanyData>(null, null)));
            }
            if (data_t.size() != 0) {
                data.add(data_t);
            }
        }

        if (userType != null) {
            Set<User> users = new HashSet<User>();
            List<User> userList = userService.selectByType(userType);
            Set<Pair<User, Pair<Company, CompanyData>>> data_t = new HashSet<Pair<User, Pair<Company, CompanyData>>>();
            if (userList != null) {
                for (User r : userList) {
                    data_t.add(new Pair<User, Pair<Company, CompanyData>>(r, new Pair<Company, CompanyData>(null, null)));
                }
            }
            if (data_t.size() != 0) {
                data.add(data_t);
            }
        }

        if (areaID != null) {
            List<Company> companies = companyService.selectByAreaID(areaID);
            Set<Pair<User, Pair<Company, CompanyData>>> data_t = new HashSet<Pair<User, Pair<Company, CompanyData>>>();
            if (companies != null) {
                for (Company r : companies) {
                    User user = userService.selectByID(r.getId());
                    if (user != null) {
                        data_t.add(new Pair<User, Pair<Company, CompanyData>>(user, new Pair<Company, CompanyData>(r, null)));
                    }
                }
            }
            if (data_t.size() != 0) {
                data.add(data_t);
            }
        }

        if (address != null && !address.isEmpty()) {
            List<Company> companies = companyService.fuzzySearchByAddress(address);
            Set<Pair<User, Pair<Company, CompanyData>>> data_t = new HashSet<Pair<User, Pair<Company, CompanyData>>>();
            if (companies != null) {
                for (Company r : companies) {
                    User user = userService.selectByID(r.getId());
                    if (user != null) {
                        data_t.add(new Pair<User, Pair<Company, CompanyData>>(user, new Pair<Company, CompanyData>(r, null)));
                    }
                }
            }
            if (data_t.size() != 0) {
                data.add(data_t);
            }
        }

        if (status != null) {
            List<CompanyData> companyDatas = companyDataService.selectByStatus(status);
            Set<Pair<User, Pair<Company, CompanyData>>> data_t = new HashSet<Pair<User, Pair<Company, CompanyData>>>();
            if (companyDatas != null) {
                for (CompanyData r : companyDatas) {
                    Company company = companyService.getCompanyById(r.getCompany_id());
                    User user = userService.selectByID(r.getCompany_id());
                    if (user != null) {
                        data_t.add(new Pair<User, Pair<Company, CompanyData>>(user, new Pair<Company, CompanyData>(company, r)));
                    }
                }
            }
            if (data_t.size() != 0) {
                data.add(data_t);
            }
        }

        if (business != null && !business.isEmpty()) {
            List<Company> companies = companyService.fuzzySearchByBusiness(business);
            Set<Pair<User, Pair<Company, CompanyData>>> data_t = new HashSet<Pair<User, Pair<Company, CompanyData>>>();
            if (companies != null) {
                for (Company r : companies) {
                    User user = userService.selectByID(r.getId());
                    if (user != null) {
                        data_t.add(new Pair<User, Pair<Company, CompanyData>>(user, new Pair<Company, CompanyData>(r, null)));
                    }
                }
            }
            if (data_t.size() != 0) {
                data.add(data_t);
            }
        }

        if (surveyTimeID != null) {
            List<CompanyData> companyDatas = companyDataService.selectBySurveyTimeID(surveyTimeID);
            Set<Pair<User, Pair<Company, CompanyData>>> data_t = new HashSet<Pair<User, Pair<Company, CompanyData>>>();
            if (companyDatas != null) {
                for (CompanyData r : companyDatas) {
                    Company company = companyService.getCompanyById(r.getCompany_id());
                    User user = userService.selectByID(r.getCompany_id());
                    if (user != null) {
                        data_t.add(new Pair<User, Pair<Company, CompanyData>>(user, new Pair<Company, CompanyData>(company, r)));
                    }
                }
            }
            if (data_t.size() != 0) {
                if (data_t.size() != 0) {
                    data.add(data_t);
                }
            }
        }

        /*最终结果*/
        Set<Pair<User, Pair<Company, CompanyData>>> finaldata = new HashSet<Pair<User, Pair<Company, CompanyData>>>();

        /*除去各条件查询的相等部分和包含部分*/
        int data_size = data.size();
        if (data_size >= 1) {
            finaldata.addAll(data.get(0));
            for (int i = 1; i < data_size; ++i) {
                Set<Pair<User, Pair<Company, CompanyData>>> finaldata_t = new HashSet<Pair<User, Pair<Company, CompanyData>>>();
                for (Pair<User, Pair<Company, CompanyData>> r : data.get(i)) {
                    for (Pair<User, Pair<Company, CompanyData>> c : finaldata) {
                        if (contain(c, r)) {
                            finaldata_t.add(c);
                        } else if (contain(r, c)) {
                            finaldata_t.add(r);
                        }
                    }
                }
                finaldata = finaldata_t;
            }
        }

        List<Area> cityList = areaService.getAllCity();
        List<Area> area = new ArrayList<Area>();
        for (Area r : cityList) {
            List area_ch = areaService.getAllAreaById(r.getId());
            area.addAll(area_ch);
        }
        model.addAttribute("data", finaldata);
        model.addAttribute("cityList", cityList);
        model.addAttribute("roleList", roleService.selectAll());
        model.addAttribute("areaList", area);
        model.addAttribute("surveyTimeList", surveyTimeService.getAllSurveyTime());
        return STATIC_PREFIX + "/search";
    }

    /**
     * 判断左元是否包含右元
     *
     * @param lhs
     * @param rhs
     * @return
     */
    private boolean contain(Pair<User, Pair<Company, CompanyData>> lhs, Pair<User, Pair<Company, CompanyData>> rhs) {
        boolean userfit = (rhs.first.equals(rhs.first));
        boolean companyfit = (rhs.second.first == null) ||
                (lhs.second.first.equals(rhs.second.first));
        boolean companyDatafit = (rhs.second.second == null) ||
                (lhs.second.second.equals(rhs.second.second));
        if (userfit && companyDatafit && companyfit) {
            return true;
        } else {
            return false;
        }
    }
}