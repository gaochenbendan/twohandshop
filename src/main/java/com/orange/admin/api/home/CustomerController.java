package com.orange.admin.api.home;

import com.orange.admin.commons.utils.SessionUtil;
import com.orange.admin.pojo.admin.bo.Result;
import com.orange.admin.pojo.admin.sc.CodeMsg;
import com.orange.admin.pojo.common.Customer;
import com.orange.admin.service.adminservice.OperaterLogService;
import com.orange.admin.service.homeservice.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OperaterLogService operaterLogService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model)
    {

        return "/home/customer/index";
    }

    @RequestMapping(value = "edit_info",method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> editIndf(Customer customer)
    {
        Customer loginedCustomer = SessionUtil.getLoginedCustomer();

        assert loginedCustomer != null;
        loginedCustomer.setAcademy(customer.getAcademy());
        loginedCustomer.setNickname(customer.getNickname());
        loginedCustomer.setGrade(customer.getGrade());
        loginedCustomer.setMobile(customer.getMobile());
        loginedCustomer.setQq(customer.getQq());
        loginedCustomer.setSchool(customer.getSchool());

        if(customerService.save(loginedCustomer) == null)
        {
            return Result.errot(CodeMsg.VALIDATE_ERROR);
        }
        operaterLogService.add("消费者日志", "修改了自己的信息:学号为【" +loginedCustomer.getId()+ "】");
        return Result.success(true);
    }

    @RequestMapping(value="/update_head_pic",method=RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> updateHeadPic(@RequestParam(name="headPic",required=true)String headPic) {

        Customer loginedCustomer = SessionUtil.getLoginedCustomer();
        assert loginedCustomer != null;
        loginedCustomer.setHeadPic(headPic);

        if(customerService.save(loginedCustomer) == null)
        {
            return Result.errot(CodeMsg.HOME_STUDENT_ADD_HEADERPIC_ERROR);
        }

        return Result.success(true);
    }

}
