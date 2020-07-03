package com.orange.admin.api.admin;

import com.orange.admin.commons.utils.MenuUtil;
import com.orange.admin.commons.utils.ValidataUtil2;
import com.orange.admin.pojo.admin.Menu;
import com.orange.admin.pojo.admin.UserMessage;
import com.orange.admin.pojo.admin.bo.Result;
import com.orange.admin.pojo.admin.sc.CodeMsg;
import com.orange.admin.service.adminservice.MenuService;
import com.orange.admin.service.adminservice.OperaterLogService;
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
 * 后端管理控制器
 */
@Controller
@RequestMapping("/menu")
public class MenuControl {

    @Autowired
    private MenuService menuService;

    @Autowired
    private OperaterLogService operaterLogService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Menu> all = menuService.findAll();
        model.addAttribute("sitename", "后台管理系统");
        model.addAttribute("title", "菜单列表");
        model.addAttribute("topMenus", MenuUtil.getTopMenus(all));
        model.addAttribute("secondMenus", MenuUtil.getSecondMenus(all));
        model.addAttribute("thirdMenus", MenuUtil.getThirdMenus(all));
        return "admin/menu/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {

        List<Menu> all = menuService.findAll();
        model.addAttribute("title", "添加");
        model.addAttribute("menus", menuService.findAll());
        model.addAttribute("sitename", "后台管理系统");
        model.addAttribute("topMenus", MenuUtil.getTopMenus(all));
        model.addAttribute("secondMenus", MenuUtil.getSecondMenus(all));

        return "admin/menu/add";
    }

    /**
     * 编辑
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model, @RequestParam(name = "menuId", required = true) Long menuId) {


        List<Menu> all = menuService.findAll();
        Menu menu_edit = menuService.findOne(new Long(menuId));
        model.addAttribute("menus", all);
        model.addAttribute("title", "编辑");
        model.addAttribute("sitename", "后台管理系统");
        model.addAttribute("topMenus", MenuUtil.getTopMenus(all));
        model.addAttribute("secondMenus", MenuUtil.getSecondMenus(all));
        model.addAttribute("menu", menu_edit);

        return "admin/menu/edit";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> delete(HttpServletRequest request, @RequestParam(name = "id", required = true) Long id) {
        try {

            menuService.delete(id);

        } catch (Exception e) {

            return Result.errot(CodeMsg.DELETE_MENU_ERROR);
        }
        UserMessage user = (UserMessage) request.getSession().getAttribute("user");

        operaterLogService.add(user.getNetName(), "删除菜单信息:ID-》【" + id + "】");


        return Result.success(true);
    }

    /**
     * 菜单添加 提交表单处理
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> add(HttpServletRequest request, Menu menu) {

        if (menu == null) {
            return Result.errot(CodeMsg.DATA_ERROR);
        }
        CodeMsg validate = ValidataUtil2.validate(menu);
        if (validate.getCode() != CodeMsg.SUCCESS.getCode()) {
            return Result.errot(validate);
        }
        if (menu.getParent() != null) {

            if (menu.getParent().getMenuId() == null) {

                menu.setParent(null);
            }

        }

        if (menuService.saveMenu(menu) == null) {
            return Result.errot(CodeMsg.ADD_MENU_ERROR);
        }
//         保存日志信息
        UserMessage user = (UserMessage) request.getSession().getAttribute("user");

        operaterLogService.add(user.getNetName(), "添加菜单信息:【" + menu.getName() + "】");


        return Result.success(true);
    }

    /**
     * 菜单修改 提交表单处理
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> edit(HttpServletRequest request, Menu menu) {

        if (menu == null) {
            return Result.errot(CodeMsg.DATA_ERROR);
        }
        if (menu.getMenuId() == null) {
            return Result.errot(CodeMsg.DATA_ERROR);
        }
        CodeMsg validate = ValidataUtil2.validate(menu);
        if (validate.getCode() != CodeMsg.SUCCESS.getCode()) {
            return Result.errot(validate);
        }
        if (menu.getParent() != null) {

            if (menu.getParent().getMenuId() == null) {

                menu.setParent(null);
            }

        }
        Menu exit = menuService.findOne(menu.getMenuId());
        if (exit == null) {
            return Result.errot(CodeMsg.DATA_ERROR);
        }
        exit.setIcon(menu.getIcon());
        exit.setSort(menu.getSort());
        exit.setName(menu.getName());
        exit.setParent(menu.getParent());
        exit.setUrl(menu.getUrl());
        exit.setButton(menu.isButton());
        exit.setShow(menu.isShow());

        if (menuService.saveMenu(exit) == null) {
            return Result.errot(CodeMsg.ADD_MENU_ERROR);
        }
//         保存日志信息
        UserMessage user = (UserMessage) request.getSession().getAttribute("user");

        operaterLogService.add(user.getNetName(), "修改菜单信息:【" + menu.getName() + "】");


        return Result.success(true);
    }


}
