package com.orange.admin.service.homeservice.Impl;

import com.orange.admin.dao.home.CustomerDao;
import com.orange.admin.pojo.common.Customer;
import com.orange.admin.service.homeservice.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 高晨
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer findBySn(String sn) {
        return customerDao.findBySn(sn);
    }

    @Override
    public Customer save(Customer customer) {
        return customerDao.save(customer);
    }
}
