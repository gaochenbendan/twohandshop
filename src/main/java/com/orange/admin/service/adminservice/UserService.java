package com.orange.admin.service.adminservice;


import com.orange.admin.commons.utils.PageUtil;
import com.orange.admin.pojo.admin.UserMessage;
import org.springframework.stereotype.Service;

/**
 * 后台操作类
 */
@Service
public interface UserService {
    /**
     * 保存
     * @param user
     */
    public void save(UserMessage user);
    /**
     * 根据名字查询
     * @param username
     */
    public UserMessage findByUser(String username);

    public PageUtil<UserMessage> findByName(UserMessage userMessage, PageUtil<UserMessage> pageBean);

    public UserMessage findByUserName(UserMessage userMessage);

    public UserMessage findById(Long id);

    public void delete(Long id);

    public Long count();

    public Boolean usernameIsExit(String username,Long id);
}
