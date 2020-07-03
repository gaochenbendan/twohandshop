package com.orange.admin.api.admin;

import com.orange.admin.commons.utils.PageUtil;
import com.orange.admin.commons.utils.ValidataUtil2;
import com.orange.admin.pojo.admin.UserMessage;
import com.orange.admin.pojo.admin.bo.Result;
import com.orange.admin.pojo.admin.sc.CodeMsg;
import com.orange.admin.pojo.common.GoodsCategory;
import com.orange.admin.service.adminservice.GoodsCategoryService;
import com.orange.admin.service.adminservice.OperaterLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * 后台物理管理控制器
 *
 * @author 高晨
 */
@Controller
@RequestMapping("/admin/good_category/")
public class GoodCategoryController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Autowired
    private OperaterLogService operaterLogService;

    @RequestMapping("/list")
    public String list(
            Model model,
            @RequestParam(name = "name", defaultValue = "") String name,
            PageUtil<GoodsCategory> pageUtil,
            GoodsCategory goodsCategory

    ) {

        model.addAttribute("title", "商品分类管理");
        model.addAttribute("name", name);
        pageUtil.setContent(new ArrayList<GoodsCategory>());
        PageUtil<GoodsCategory> all = goodsCategoryService.findList(pageUtil, goodsCategory);
        System.out.println("-------------");
        System.out.println(all.getContent());
        System.out.println("-------------");
        model.addAttribute("pageBean",all);
        return "admin/good_category/list";

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, GoodsCategory goodsCategory) {

        model.addAttribute("title", "添加-物品分类");
        model.addAttribute("goodsCategorys",goodsCategoryService.findTopCategory());
        return "admin/good_category/add";

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> add(HttpServletRequest request, GoodsCategory goodsCategory) {
        if (goodsCategory == null) {
            return Result.errot(CodeMsg.GOODERCATEGORY_ADD_NULL);
        }

        CodeMsg validate = ValidataUtil2.validate(goodsCategory);
        if (validate.getCode() != CodeMsg.SUCCESS.getCode()) {
            return Result.errot(validate);
        }


        if(goodsCategory.getParent() == null || goodsCategory.getParent().getId()==null)
        {
            goodsCategory.setParent(null);
        }
        if (goodsCategoryService.addGoodsCategory(goodsCategory) == null) {
            return Result.errot(CodeMsg.GOODERCATEGORY_ADD_ERROR);
        }

        UserMessage user = (UserMessage) request.getSession().getAttribute("user");

        operaterLogService.add(user.getNetName(), "添加物品分类:名称-》【" + goodsCategory.getName() + "】");


        return Result.success(true);

    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,@RequestParam(required = true,name = "id") Long id, GoodsCategory goodsCategory) {

        model.addAttribute("title", "编辑-物品分类");
        model.addAttribute("goodsCategorys",goodsCategoryService.findTopCategory());
        model.addAttribute("goodsCategory",goodsCategoryService.finfById(id));

        return "admin/good_category/edit";

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> edit(HttpServletRequest request,GoodsCategory goodsCategory) {
        if (goodsCategory == null) {
            return Result.errot(CodeMsg.GOODERCATEGORY_ADD_NULL);
        }

        CodeMsg validate = ValidataUtil2.validate(goodsCategory);
        if (validate.getCode() != CodeMsg.SUCCESS.getCode()) {
            return Result.errot(validate);
        }


        if(goodsCategory.getParent() == null || goodsCategory.getParent().getId()==null)
        {
            goodsCategory.setParent(null);
        }
        if(goodsCategory.getId() == null)
        {
            return Result.errot(CodeMsg.GOODERCATEGORY_EDIT_ERROR);
        }

        GoodsCategory goodsCategory1 = goodsCategoryService.finfById(goodsCategory.getId());
        if(goodsCategory1 == null)
        {
            return Result.errot(CodeMsg.GOODERCATEGORY_EDIT_ERROR);
        }
        goodsCategory1.setName(goodsCategory.getName());
        goodsCategory1.setIcon(goodsCategory.getIcon());
        goodsCategory1.setParent(goodsCategory.getParent());
        goodsCategory1.setSort(goodsCategory.getSort());


        if (goodsCategoryService.addGoodsCategory(goodsCategory1) == null) {
            return Result.errot(CodeMsg.GOODERCATEGORY_EDIT_ERROR);
        }

        UserMessage user = (UserMessage) request.getSession().getAttribute("user");

        operaterLogService.add(user.getNetName(), "修改物品分类:名称-》【" + goodsCategory.getName() + "】");



        return Result.success(true);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> delete(HttpServletRequest request,@RequestParam(name = "id",required = true) Long id) {

        if(id == null)
        {
            return Result.errot(CodeMsg.GOODERCATEGORY_DELETE_ERROR);
        }

        try {
            goodsCategoryService.delteById(id);
        }catch (Exception e)
        {
            return Result.errot(CodeMsg.GOODERCATEGORY_DELETE_ERROR_CHILREN);
        }

        UserMessage user = (UserMessage) request.getSession().getAttribute("user");

        operaterLogService.add(user.getNetName(), "删除物品分类:ID-》【" + id + "】");


        return Result.success(true);

    }


}
