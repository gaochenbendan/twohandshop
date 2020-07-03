package com.orange.admin.api.admin;

import com.alibaba.fastjson.JSONArray;
import com.orange.admin.commons.utils.MenuUtil;
import com.orange.admin.commons.utils.PageUtil;
import com.orange.admin.commons.utils.ValidataUtil2;
import com.orange.admin.pojo.admin.Menu;
import com.orange.admin.pojo.admin.Role;
import com.orange.admin.pojo.admin.UserMessage;
import com.orange.admin.pojo.admin.bo.Result;
import com.orange.admin.pojo.admin.sc.CodeMsg;
import com.orange.admin.service.adminservice.MenuService;
import com.orange.admin.service.adminservice.OperaterLogService;
import com.orange.admin.service.adminservice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 后台角色管理控制器
 */
@Controller
@RequestMapping("/role")
public class RoleControl {


    @Autowired
    private MenuService menuService;

    @Autowired
    private OperaterLogService operaterLogService;

    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "/list")
    public String list(
            Model model, Role role, PageUtil<Role> pageUtil) {

        model.addAttribute("title", "角色管理");
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("name", role.getName());
        model.addAttribute("pageBean", roleService.findByName(role, pageUtil));
        return "admin/role/list";
    }

    /**
     * 添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        List<Menu> all = menuService.findAll();
        model.addAttribute("topMenus", MenuUtil.getTopMenus(all));
        model.addAttribute("secondMenus", MenuUtil.getSecondMenus(all));
        model.addAttribute("thirdMenus", MenuUtil.getThirdMenus(all));
        model.addAttribute("title", "添加角色");
        return "admin/role/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> add(Role role, HttpServletRequest request) {

        System.out.println(role);
        CodeMsg validate = ValidataUtil2.validate(role);
        if (validate.getCode() != CodeMsg.getSUCCESS().getCode()) {
            return Result.errot(validate);
        }
        if (roleService.save(role) == null) {
            return Result.errot(CodeMsg.SAVE_ROLE_ERROR);
        }

        UserMessage user = (UserMessage) request.getSession().getAttribute("user");
        operaterLogService.add(user.getNetName(), "添加角色信息:名称-》【" + role.getName() + "】");
        return Result.success(true);
    }

    /**
     * 编辑
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(name = "id", required = true) Long id, Model model) {
        List<Menu> all = menuService.findAll();
        model.addAttribute("topMenus", MenuUtil.getTopMenus(all));
        model.addAttribute("secondMenus", MenuUtil.getSecondMenus(all));
        model.addAttribute("thirdMenus", MenuUtil.getThirdMenus(all));
        model.addAttribute("title", "编辑角色");
        Role byId = roleService.findById(id);
        model.addAttribute("role", byId);
        model.addAttribute("authorities", JSONArray.toJSON(byId.getAuthorities()).toString());
        return "admin/role/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> edit(Role role, HttpServletRequest request) {
        //用统一验证实体方法验证是否合法
        CodeMsg validate = ValidataUtil2.validate(role);
        if (validate.getCode() != CodeMsg.SUCCESS.getCode()) {
            return Result.errot(validate);
        }
        Role existRole = roleService.findById(role.getId());
        if (existRole == null) {
            return Result.errot(CodeMsg.ADMIN_ROLE_NO_EXIST);
        }
        existRole.setName(role.getName());
        existRole.setRemark(role.getRemark());
        existRole.setStatus(role.getStatus());
        existRole.setAuthorities(role.getAuthorities());
        if (roleService.save(existRole) == null) {
            return Result.errot(CodeMsg.ADMIN_ROLE_NO_EXIST);
        }
        UserMessage user = (UserMessage) request.getSession().getAttribute("user");
        operaterLogService.add(user.getNetName(), "修改角色信息:名称-》【" + role.getName() + "】");
        return Result.success(true);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> delete(@RequestParam(name = "id", required = true) Long id, HttpServletRequest request) {
        try {
            roleService.deleteById(id);
        } catch (Exception e) {
            // TODO: handle exception
            return Result.errot(CodeMsg.ADMIN_ROLE_DELETE_ERROR);
        }

        UserMessage user = (UserMessage) request.getSession().getAttribute("user");
        operaterLogService.add(user.getNetName(), "删除角色信息:ID-》【" + id + "】");
        return Result.success(true);
    }

}
