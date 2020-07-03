package com.orange.admin.service.adminservice.Impl;

import com.orange.admin.commons.utils.PageUtil;
import com.orange.admin.commons.utils.StringUtil;
import com.orange.admin.dao.admin.DatabaseDakDao;
import com.orange.admin.pojo.admin.DatabaseBak;
import com.orange.admin.service.adminservice.DatabaseDakService;
import com.orange.admin.service.adminservice.OperaterLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

/**
 * @author 高晨
 */
@Service
public class DatabaseDakServiceImpl implements DatabaseDakService {

    @Autowired
    private DatabaseDakDao databaseDakDao;

    @Autowired
    private OperaterLogService operaterLogService;

    @Autowired
    private DatabaseDakService databaseDakService;

    @Value("${orange.upload.photo.username}")
    private String username;

    @Value("${orange.upload.photo.data_bak_path}")
    private String databaseBakPath;

    @Value("${orange.upload.photo.password}")
    private String password;

    @Value("${orange.upload.photo.database}")
    private String database;

    @Override
    public PageUtil<DatabaseBak> list(PageUtil<DatabaseBak> pageUtil) {


        Pageable pageable = PageRequest.of(pageUtil.getCurrentPage()-1, pageUtil.getPageSize(), Sort.by(Sort.Direction.DESC,"createTime"));
        Page<DatabaseBak> findAll = databaseDakDao.findAll( pageable);
        pageUtil.setContent(findAll.getContent());
        pageUtil.setTotal(findAll.getTotalElements());
        pageUtil.setTotalPage(findAll.getTotalPages());


        return pageUtil;
    }

    @Override
    public DatabaseBak save(DatabaseBak databaseBak) {
        return databaseDakDao.save(databaseBak);
    }

    @Override
    public void removeDatabeBak(Long id) {
        databaseDakDao.deleteById(id);
    }

    @Override
    public DatabaseBak findOne(Long id) {
        return databaseDakDao.findById(id).orElse(null);
    }

    @Override
    public void restoreById(Long id) {
        DatabaseBak databaseBak = findOne(id);
        try {
            String filename = databaseBak.getFilename();
            File file = new File(databaseBak.getFilepath() + databaseBak.getFilename());
            String cmd = "mysql -u"+username+" -p"+password+" "+ database+" < " + databaseBakPath + filename;
            if(!file.exists()){
                cmd = "mysql -u"+username+" -p"+password+" "+ database+" < " + databaseBak.getFilepath() + filename;
            }
            String stmt1 = "mysqladmin -u "+username+" -p"+password+" create "+database;
            String[] cmds = { "cmd", "/c", cmd };
            Runtime.getRuntime().exec(stmt1);
            Process exec = Runtime.getRuntime().exec(cmds);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void backUp() {
        File file = new File(databaseBakPath);
        if(!file.exists()){
            file.mkdir();
        }

        try {
            Date date = new Date();
            String yyyyMMdd = StringUtil.getFormatterDate(date, "yyyyMMddhhmmss");
            String filepath = databaseBakPath+yyyyMMdd+".sql";
            String cmd = "mysqldump -u"+username+" -p"+password+" "+database+" -r "+filepath;
            System.out.println(cmd);
//           执行cmd
            Runtime.getRuntime().exec(cmd);
//            没有问题的话 保存到数据库
            DatabaseBak databaseBak = new DatabaseBak();
            databaseBak.setFilename(yyyyMMdd+".sql");
            databaseBak.setFilepath(databaseBakPath);

            try {
                databaseDakService.save(databaseBak);
            }catch (Exception e)
            {
                e.printStackTrace();
            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public Long count() {
        return databaseDakDao.count();
    }
}
