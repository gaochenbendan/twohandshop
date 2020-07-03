package com.orange.admin.api.admin;

import com.orange.admin.commons.utils.PageUtil;
import com.orange.admin.commons.utils.ValidataUtil2;
import com.orange.admin.pojo.admin.UserMessage;
import com.orange.admin.pojo.admin.bo.Result;
import com.orange.admin.pojo.admin.sc.CodeMsg;
import com.orange.admin.service.adminservice.OperaterLogService;
import com.orange.admin.service.adminservice.RoleService;
import com.orange.admin.service.adminservice.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 用户管理控制器
 */
@Controller
@RequestMapping("/user")
public class UserControl {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @Autowired
    private OperaterLogService operaterLogService;

    /**
     * 用户列表
     *
     * @param model
     * @param userMessage
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Model model, UserMessage userMessage, PageUtil<UserMessage> pageBean) {

        model.addAttribute("title", "用户列表");
        model.addAttribute("netName", userMessage.getNetName());
        PageUtil<UserMessage> byName = userService.findByName(userMessage, pageBean);
        System.out.println(byName);
        model.addAttribute("pageBean", userService.findByName(userMessage, pageBean));
        return "admin/user/list";
    }

    /**
     * 用户添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {

        model.addAttribute("roles", roleService.findAll());
        return "admin/user/add";
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> add(HttpServletRequest request, UserMessage user) {
//        验证
        CodeMsg validate = ValidataUtil2.validate(user);
        if (validate.getCode() != CodeMsg.getSUCCESS().getCode()) {
            return Result.errot(validate);
        }
//        判断角色管理
        if (user.getRole() == null || user.getRole().getId() == null) {
            return Result.errot(CodeMsg.USER_ADD_ROLE_ERROR);
        }
//        通过用户名查找
        UserMessage byUserName = userService.findByUserName(user);
//        判断用户名是否重复
        if (byUserName != null) {
            return Result.errot(CodeMsg.USER_NAME_HAVE_EXITS);
        }
//        保存数据
        try {
            userService.save(user);
        } catch (Exception e) {
            return Result.errot(CodeMsg.ADD_USER_ERROR);
        }
        UserMessage user1 = (UserMessage) request.getSession().getAttribute("user");
        operaterLogService.add(user1.getUserName(), "新增角色信息:ID-》【" + user.getId() + "】");
        return Result.success(true);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model, @RequestParam(required = true, name = "id") Long id) {

        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user2", userService.findById(id));
        return "admin/user/edit";
    }


    /**
     * 编辑用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> edit(UserMessage user, HttpServletRequest request) {
//        验证
        CodeMsg validate = ValidataUtil2.validate(user);
        if (validate.getCode() != CodeMsg.getSUCCESS().getCode()) {
            return Result.errot(validate);
        }
//        判断角色管理
        if (user.getRole() == null || user.getRole().getId() == null) {
            return Result.errot(CodeMsg.USER_ADD_ROLE_ERROR);
        }
//        通过用户名查找
        UserMessage byUserName = userService.findByUserName(user);
//        判断用户名是否重复
        if (userService.usernameIsExit(user.getNetName(), user.getId())) {
            return Result.errot(CodeMsg.USER_NAME_HAVE_EXITS);
        }

        UserMessage byId = userService.findById(user.getId());
        BeanUtils.copyProperties(user, byId, "id", "createTime", "updateTime");

//        保存数据s
        try {
            byId.setUpdataTime(new Date());
            userService.save(byId);
            UserMessage user1 = (UserMessage) request.getSession().getAttribute("user");
            operaterLogService.add(user1.getUserName(), "编辑角色信息:ID-》【" + byId.getId() + "】");


//

        } catch (Exception e) {
            return Result.errot(CodeMsg.ADD_USER_ERROR);
        }


        return Result.success(true);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> delete(HttpServletRequest request, @RequestParam(name = "id", required = true) Long id) {

        try {
            userService.delete(id);
        } catch (Exception e) {
            return Result.errot(CodeMsg.DELETE_USER_ERROR);
        }
        UserMessage user1 = (UserMessage) request.getSession().getAttribute("user");
        operaterLogService.add(user1.getUserName(), "新增角色信息:ID-》【" + id + "】");

        return Result.success(true);
    }


}
