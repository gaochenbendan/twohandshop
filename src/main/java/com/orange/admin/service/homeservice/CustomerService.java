package com.orange.admin.service.homeservice;

import com.orange.admin.pojo.common.Customer;
import org.springframework.stereotype.Service;

/**
 * @author 高晨
 */
@Service
public interface CustomerService {

    /**
     * 根据学号查询
     *
     * @param sn
     * @return
     */
    public Customer findBySn(String sn);

    /**
     * 保存数据
     *
     * @param customer
     * @return
     */
    public Customer save(Customer customer);

}
