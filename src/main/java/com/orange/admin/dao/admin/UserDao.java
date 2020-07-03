package com.orange.admin.dao.admin;


import com.orange.admin.pojo.admin.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserMessage,Long> {

    public UserMessage findByUserName(String username);

}
