package com.orange.admin.service.adminservice;

import com.orange.admin.commons.utils.PageUtil;
import com.orange.admin.pojo.admin.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    public Role save(Role role);

    public List<Role> findAll();

    public Role findById(Long id);

    public void deleteById(Long id);

    public PageUtil<Role> findByName(Role role, PageUtil<Role> pageBean);
}
