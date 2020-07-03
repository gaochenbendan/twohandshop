package com.orange.admin.service.adminservice;

import com.orange.admin.commons.utils.PageUtil;
import com.orange.admin.pojo.admin.DatabaseBak;
import org.springframework.stereotype.Service;

/**
 * 数据库备份
 */
@Service
public interface DatabaseDakService {

    public PageUtil<DatabaseBak> list(PageUtil<DatabaseBak> pageUtil);

    public DatabaseBak save(DatabaseBak databaseBak);

    public void removeDatabeBak(Long id);

    public DatabaseBak findOne(Long id);

    public void restoreById(Long id);

    public void backUp();

    public Long count();

}
