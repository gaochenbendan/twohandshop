package com.orange.admin.api.admin;


import com.orange.admin.Interceptor.admin.HttpSessionListion;
import com.orange.admin.commons.utils.PageUtil;
import com.orange.admin.commons.utils.SessionUtil;
import com.orange.admin.commons.utils.StringUtil;
import com.orange.admin.commons.utils.ValidataUtil2;
import com.orange.admin.pojo.admin.OperaterLog;
import com.orange.admin.pojo.admin.Role;
import com.orange.admin.pojo.admin.UserMessage;
import com.orange.admin.pojo.admin.bo.Result;
import com.orange.admin.pojo.admin.sc.CodeMsg;
import com.orange.admin.pojo.admin.vo.UserVo;
import com.orange.admin.service.adminservice.DatabaseDakService;
import com.orange.admin.service.adminservice.OperaterLogService;
import com.orange.admin.service.adminservice.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Controller
@RequestMapping("/system")
@Api(value = "测试的模板", tags = "测试的模板")
public class SystemControl {


    @Autowired
    private UserService userService;

    @Autowired
    private OperaterLogService operaterLogService;

    @Autowired
    private DatabaseDakService databaseDakService;


    /**
     * 登录接口
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("sitename", "橘子(Orange Admin)后台管理系统模板");

        return "admin/system/login";
    }

    /**
     * 登录主页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("sitename", "后台管理系统");
        model.addAttribute("operaterLogData", operaterLogService.findLastData(10));
        model.addAttribute("uerTotal", userService.count());
        model.addAttribute("operatoreLogTotal", operaterLogService.count());
        model.addAttribute("databaseBakTotal", databaseDakService.count());
        model.addAttribute("onlineUserTotal", HttpSessionListion.onlineUserCoutent);

        return "admin/system/index";
    }

    /**
     * 登录验证
     *
     * @param request
     * @param userVo
     * @param cpacha
     * @return
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> login(
            HttpServletRequest request,
            UserVo userVo,
            String cpacha) throws IllegalAccessException {


        if (userVo == null) {
            return Result.errot(CodeMsg.DATA_ERROR);
        }

        CodeMsg validata = ValidataUtil2.validate(userVo);
        if (validata.getCode() != CodeMsg.getSUCCESS().getCode()) {
            return Result.errot(validata);
        }
//        验证码

        if (StringUtils.isEmpty(cpacha)) {
            return Result.errot(CodeMsg.CPACHA_EMPTY);
        }
        Object cpachaData = request.getSession().getAttribute("admin_login");
        if (cpachaData == null) {
//            验证码超时
            return Result.errot(CodeMsg.CPACHA_TIMEOUT);
        }
//        验证码验证
        if (!cpacha.equalsIgnoreCase(cpachaData.toString())) {
            return Result.errot(CodeMsg.ADMIN_CPACHA_EMPTY);
        }
//        验证码开始正确 开始验证账号密码
        UserMessage byUser = userService.findByUser(userVo.getUsername());
        if (byUser == null) {
            return Result.errot(CodeMsg.USERNAME_NO_IS_EXIT);
        }
//        验证密码
        if (!byUser.getPassword().equals(userVo.getPassword())) {
            return Result.errot(CodeMsg.PASSWORD_ERROR);
        }
//      判断用户是否可用
        if (byUser.getStatus() == UserMessage.ADMIN_USER_STATUS_UNABLE) {
            return Result.errot(CodeMsg.USER_UNABLE);
        }
//      判断用户角色是否可用
        if (byUser.getRole().getStatus() == Role.ADMIN_ROLE_STATUS_UNABLE || byUser.getRole() == null) {
            return Result.errot(CodeMsg.USER_UNABLE);
        }

//        将登录信息放入SESSION
        request.getSession().setAttribute("user", byUser);
//        销毁验证码
        request.getSession().setAttribute("admin_login", null);
//         写入日志库
        OperaterLog operaterLog = new OperaterLog();
        operaterLog.setOperator(byUser.getNetName());
        operaterLog.setContent("【" + byUser.getNetName() + "】登录于：【" + StringUtil.getFormatterDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "】");
        operaterLog.setSiteName("【登录于山西运城】");
        operaterLogService.save(operaterLog);


        return Result.success(true);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {

        try {
            SessionUtil.set("user",null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "admin/system/login";
    }

    /**
     * 无权限提示页面
     *
     * @return
     */
    @RequestMapping(value = "/noright", method = RequestMethod.GET)
    public String noRight() {
        return "admin/system/noright";
    }

    @RequestMapping(value = "/update_userinfo", method = RequestMethod.GET)
    public String update_userinfo() {
        return "admin/system/updateUserinfo";
    }

    @RequestMapping(value = "/update_userinfo", method = RequestMethod.POST)
    public String update_userinfo(HttpServletRequest request, UserMessage userMessage) {

        System.out.println(userMessage.toString());

        UserMessage user = (UserMessage) request.getSession().getAttribute("user");
        user.setHeadPic(userMessage.getHeadPic());
        user.setPhone(userMessage.getPhone());
        user.setSex(userMessage.getSex());
        user.setEmail(userMessage.getEmail());
        user.setNetName(userMessage.getNetName());

        request.getSession().setAttribute("user", user);

        try {
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:update_userinfo";
    }

    /**
     * 修改密码页面
     *
     * @return
     */
    @RequestMapping(value = "/update_pwd", method = RequestMethod.GET)
    public String updatePwd() {
        return "admin/system/updatePwd";
    }

    @RequestMapping(value = "/update_pwd", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> updatePwd(
            @RequestParam(name = "oldpwd", required = true)
                    String oldpwd,
            @RequestParam(name = "newpwd", required = true)
                    String newpwd,
            HttpServletRequest request
    ) {

        UserMessage user = (UserMessage) request.getSession().getAttribute("user");
        if (!user.getPassword().equals(oldpwd)) {
            return Result.errot(CodeMsg.OLD_PWD_ERROR);
        }
        if (StringUtils.isEmpty(newpwd)) {
            return Result.errot(CodeMsg.NEW_EMPTY);
        }
        user.setPassword(newpwd);
        userService.save(user);
//       更新SESSION
        request.getSession().setAttribute("user", user);
        return Result.success(true);
    }

    @RequestMapping(value = "/operator_log_list")
    public String operator_log_list(OperaterLog operaterLog, PageUtil<OperaterLog> pageUtil, Model model) {
        PageUtil<OperaterLog> list = operaterLogService.findList(operaterLog, pageUtil);
        model.addAttribute("pageBean", list);
        model.addAttribute("operator", operaterLog.getOperator());
        model.addAttribute("title", "日志列表");
        return "admin/system/operator_log_list";
    }

    @RequestMapping(value = "/delete_log_list", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> delete_log_list(String ids) {

        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                operaterLogService.deleteList(Long.valueOf(s));
            }
        } else {
            return Result.errot(CodeMsg.LOG_DELETE_NULL);
        }

        return Result.success(true);
    }

    @RequestMapping(value = "/delete_log_list_all", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> delete_log_list_all() {

        try {
            operaterLogService.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.success(true);
    }
}
