package com.orange.admin.service.adminservice;


import com.orange.admin.commons.utils.PageUtil;
import com.orange.admin.pojo.admin.OperaterLog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台操作类
 */
@Service
public interface OperaterLogService {

    /**
     * 添加/修改操作日志，当id不为空时，修改，id为空时自动新增一条记录
     * @param operaterLog
     * @return
     */
    public OperaterLog save(OperaterLog operaterLog);

    public OperaterLog findById(Long id);

    public List<OperaterLog> findAll();

    /**
     * 获取指定条数的参数列表
     * @param size
     * @return
     */
    public List<OperaterLog> findLastData(int size);

    public void add(String operator,String contain);

    public PageUtil<OperaterLog> findList(OperaterLog operaterLog, PageUtil<OperaterLog> pageUtil);

    public void deleteList(Long id);
    public void deleteAll();

    public Long count();
}
