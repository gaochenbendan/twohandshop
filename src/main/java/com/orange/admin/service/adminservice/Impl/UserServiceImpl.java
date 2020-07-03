package com.orange.admin.service.adminservice.Impl;

import com.orange.admin.commons.utils.PageUtil;
import com.orange.admin.dao.admin.UserDao;
import com.orange.admin.pojo.admin.UserMessage;
import com.orange.admin.service.adminservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * @author 高晨
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public void save(UserMessage user) {
        userDao.save(user);
    }

    @Override
    public UserMessage findByUser(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public PageUtil<UserMessage> findByName(UserMessage userMessage, PageUtil<UserMessage> pageBean) {

        ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("netName", ExampleMatcher.GenericPropertyMatchers.contains());
        withMatcher = withMatcher.withIgnorePaths("status","sex");
        Example<UserMessage> example = Example.of(userMessage, withMatcher);
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
        Page<UserMessage> findAll = userDao.findAll(example, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());

        return pageBean;
    }

    @Override
    public UserMessage findByUserName(UserMessage userMessage) {

        return userDao.findByUserName(userMessage.getUserName());
    }

    @Override
    public UserMessage findById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public Long count() {
        return userDao.count();
    }

    @Override
    public Boolean usernameIsExit(String username, Long id) {
        UserMessage byUserName = userDao.findByUserName(username);
        if(byUserName!=null){
            if(byUserName.getId().longValue() != id.longValue()){
                return true;
            }
        }

        return false;
    }
}
