package com.orange.admin.service.adminservice.Impl;

import com.orange.admin.commons.utils.PageUtil;
import com.orange.admin.dao.admin.OperaterLogDao;
import com.orange.admin.pojo.admin.OperaterLog;
import com.orange.admin.service.adminservice.OperaterLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 高晨
 */
@Service

public class OperaterLogServiceImpl implements OperaterLogService {

    @Autowired
    OperaterLogDao operaterLogDao;

    @Override
    public OperaterLog save(OperaterLog operaterLog) {
        return operaterLogDao.save(operaterLog);
    }

    @Override
    public OperaterLog findById(Long id) {
        return operaterLogDao.getOne(id);
    }

    @Override
    public List<OperaterLog> findAll() {
        return operaterLogDao.findAll();
    }

    @Override
    public List<OperaterLog> findLastData(int size) {
        return operaterLogDao.findLastData(size);

    }

    @Override
    public void add(String operator, String contain) {
        OperaterLog operaterLog = new OperaterLog();
        operaterLog.setOperator(operator);
        operaterLog.setContent(contain);
        operaterLog.setSiteName("【山西运城】");
        operaterLogDao.save(operaterLog);
    }

    @Override
    public PageUtil<OperaterLog> findList(OperaterLog operaterLog, PageUtil<OperaterLog> pageUtil) {
        ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("operator", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<OperaterLog> example = Example.of(operaterLog, withMatcher);
        Pageable pageable = PageRequest.of(pageUtil.getCurrentPage()-1, pageUtil.getPageSize(),Sort.by(Sort.Direction.DESC,"createTime"));
        Page<OperaterLog> findAll = operaterLogDao.findAll(example, pageable);
        pageUtil.setContent(findAll.getContent());
        pageUtil.setTotal(findAll.getTotalElements());
        pageUtil.setTotalPage(findAll.getTotalPages());
        return pageUtil;
    }

    @Override
    public void deleteList(Long id) {
        operaterLogDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        operaterLogDao.deleteAll();
    }

    @Override
    public Long count() {
        return operaterLogDao.count();
    }
}
