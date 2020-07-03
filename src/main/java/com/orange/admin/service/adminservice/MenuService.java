package com.orange.admin.service.adminservice;

import com.orange.admin.pojo.admin.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 高晨
 */
@Service
public interface MenuService {

    /**
     * 保存
     * @param menu
     */
    public Menu saveMenu(Menu menu);
    /**
     * 查询所有
     */
    public List<Menu> findAll();

    public Menu findOne(Long id);

    public void delete(Long id);

}
