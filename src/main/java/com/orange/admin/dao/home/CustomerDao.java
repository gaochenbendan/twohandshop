package com.orange.admin.dao.home;

import com.orange.admin.pojo.common.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 高晨
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer,Long> {

    /**
     * 据学校查询根
     *
     * @param sn
     * @return
     */
    public Customer findBySn(String sn);

}
