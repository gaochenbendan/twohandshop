package com.orange.admin.api.admin;

import com.orange.admin.commons.utils.PageUtil;
import com.orange.admin.pojo.admin.DatabaseBak;
import com.orange.admin.pojo.admin.UserMessage;
import com.orange.admin.pojo.admin.bo.Result;
import com.orange.admin.pojo.admin.sc.CodeMsg;
import com.orange.admin.service.adminservice.DatabaseDakService;
import com.orange.admin.service.adminservice.OperaterLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/database_bak")
public class databaseBakControl {

    @Autowired
    private OperaterLogService operaterLogService;

    @Autowired
    private DatabaseDakService databaseDakService;

    @Value("${orange.upload.photo.username}")
    private String username;

    @Value("${orange.upload.photo.data_bak_path}")
    private String databaseBakPath;

    @Value("${orange.upload.photo.password}")
    private String password;

    @Value("${orange.upload.photo.database}")
    private String database;

    /**
     * 数据库备份文件列表页面
     *
     * @param model
     * @param pageUtil
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model, PageUtil<DatabaseBak> pageUtil) {

        model.addAttribute("pageBean", databaseDakService.list(pageUtil));
        model.addAttribute("title", "数据库备份");
        return "/admin/databasebak/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> add(HttpServletRequest request) {
        databaseDakService.backUp();

        return Result.success(true);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> delete(String ids, HttpServletRequest request) {

        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                DatabaseBak one = databaseDakService.findOne(Long.valueOf(s));
                if (one != null) {
                    databaseDakService.removeDatabeBak(Long.valueOf(s));
                    File file = new File(one.getFilepath() + one.getFilename());
                    if (!file.exists()) {
                        file = new File(databaseBakPath + one.getFilename());
                    }
                    file.delete();
                    UserMessage user = (UserMessage) request.getSession().getAttribute("user");
                    operaterLogService.add(user.getNetName(), "【数据库删除】删除的文件名：【" + one.getFilename() + "】");
                }


            }
        } else {
            return Result.errot(CodeMsg.DATABASE_BAK_ID_EMPTY);
        }

        return Result.success(true);
    }

    /**
     * 还原数据库文件
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/restore", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> restore(@RequestParam(name = "id", required = true) Long id) {
        databaseDakService.restoreById(id);

        return Result.success(true);
    }


}
