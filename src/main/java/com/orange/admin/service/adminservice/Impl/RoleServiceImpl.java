package com.orange.admin.service.adminservice.Impl;

import com.orange.admin.commons.utils.PageUtil;
import com.orange.admin.dao.admin.RoleDao;
import com.orange.admin.pojo.admin.Role;
import com.orange.admin.service.adminservice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 高晨
 */
@Service

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findById(Long id) {


        return roleDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        roleDao.deleteById(id);
    }

    @Override
    public PageUtil<Role> findByName(Role role, PageUtil<Role> pageBean) {
        ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        withMatcher = withMatcher.withIgnorePaths("status");
        Example<Role> example = Example.of(role, withMatcher);
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
        Page<Role> findAll = roleDao.findAll(example, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }


}
