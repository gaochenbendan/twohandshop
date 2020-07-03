package com.orange.admin.service.adminservice.Impl;

import com.orange.admin.dao.admin.MenuDao;
import com.orange.admin.pojo.admin.Menu;
import com.orange.admin.service.adminservice.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public Menu saveMenu(Menu menu) {

        return menuDao.save(menu);
    }

    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Override
    public Menu findOne(Long id) {

        return menuDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
       menuDao.deleteById(id);
    }
}
